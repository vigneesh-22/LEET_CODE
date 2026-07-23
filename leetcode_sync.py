#!/usr/bin/env python3
"""Download accepted LeetCode submissions and publish them with Git.

Only Python's standard library is used.  Authentication is supplied through
environment variables so account cookies never need to be committed.
"""

from __future__ import annotations

import argparse
import json
import os
import re
import subprocess
import sys
from datetime import datetime, timezone
from pathlib import Path
from typing import Any
from urllib.error import HTTPError, URLError
from urllib.request import Request, urlopen


API_URL = "https://leetcode.com/graphql/"
ROOT = Path(__file__).resolve().parent
PROBLEMS_DIR = ROOT / "leetcode"
MANIFEST = PROBLEMS_DIR / ".synced_submissions.json"

EXTENSIONS = {
    "python3": ".py", "python": ".py", "cpp": ".cpp", "c++": ".cpp",
    "java": ".java", "javascript": ".js", "typescript": ".ts",
    "c": ".c", "csharp": ".cs", "golang": ".go", "rust": ".rs",
    "kotlin": ".kt", "swift": ".swift", "ruby": ".rb", "php": ".php",
    "scala": ".scala", "dart": ".dart", "racket": ".rkt",
}

RECENT_QUERY = """
query recentAcSubmissions($username: String!, $limit: Int!) {
  recentAcSubmissionList(username: $username, limit: $limit) {
    title titleSlug timestamp statusDisplay lang submissionId
  }
}
"""

DETAIL_QUERY = """
query submissionDetails($submissionId: Int!) {
  submissionDetails(submissionId: $submissionId) {
    code lang runtime memory timestamp
    question { questionId title titleSlug difficulty }
  }
}
"""


def fail(message: str) -> None:
    print(f"Error: {message}", file=sys.stderr)
    raise SystemExit(1)


def graphql(query: str, variables: dict[str, Any]) -> dict[str, Any]:
    session = os.environ.get("LEETCODE_SESSION")
    if not session:
        fail("set LEETCODE_SESSION first (see README)")

    csrf = os.environ.get("LEETCODE_CSRFTOKEN", "")
    body = json.dumps({"query": query, "variables": variables}).encode()
    cookies = f"LEETCODE_SESSION={session}"
    if csrf:
        cookies += f"; csrftoken={csrf}"
    request = Request(API_URL, data=body, method="POST", headers={
        "Content-Type": "application/json",
        "Accept": "application/json",
        "Cookie": cookies,
        "Referer": "https://leetcode.com/",
        "User-Agent": "leetcode-local-sync/1.0",
        **({"x-csrftoken": csrf} if csrf else {}),
    })
    try:
        with urlopen(request, timeout=30) as response:
            result = json.loads(response.read().decode())
    except HTTPError as error:
        fail(f"LeetCode returned HTTP {error.code}. Refresh your session cookie.")
    except URLError as error:
        fail(f"could not reach LeetCode: {error.reason}")
    if result.get("errors"):
        fail("LeetCode API error: " + result["errors"][0].get("message", "unknown error"))
    return result["data"]


def safe_name(value: str) -> str:
    value = re.sub(r"[^A-Za-z0-9]+", "_", value).strip("_").lower()
    return value or "solution"


def load_manifest() -> dict[str, int]:
    if not MANIFEST.exists():
        return {}
    try:
        return json.loads(MANIFEST.read_text())
    except json.JSONDecodeError:
        fail(f"invalid sync manifest: {MANIFEST}")


def write_solution(detail: dict[str, Any]) -> Path:
    question = detail["question"]
    difficulty = question["difficulty"].lower()
    if difficulty not in {"easy", "medium", "hard"}:
        fail(f"unexpected difficulty: {question['difficulty']}")
    extension = EXTENSIONS.get(detail["lang"].lower(), ".txt")
    filename = f"{int(question['questionId']):04d}_{safe_name(question['titleSlug'])}{extension}"
    target = PROBLEMS_DIR / difficulty / filename
    target.parent.mkdir(parents=True, exist_ok=True)
    submitted = datetime.fromtimestamp(int(detail["timestamp"]), tz=timezone.utc).isoformat()
    header = (
        f"# LeetCode {question['questionId']}: {question['title']}\n"
        f"# Difficulty: {question['difficulty']} | Language: {detail['lang']} | Accepted: {submitted}\n"
        f"# https://leetcode.com/problems/{question['titleSlug']}/\n\n"
    )
    # Keep source files valid for common non-# comment languages.
    if extension in {".cpp", ".c", ".java", ".js", ".ts", ".cs", ".go", ".kt", ".swift", ".php", ".dart", ".rs"}:
        header = "\n".join("//" + line[1:] if line.startswith("#") else "//" + line for line in header.splitlines()) + "\n\n"
    target.write_text(header + detail["code"].rstrip() + "\n")
    return target


def sync(args: argparse.Namespace) -> None:
    manifest = load_manifest()
    recent = graphql(RECENT_QUERY, {"username": args.username, "limit": args.limit})["recentAcSubmissionList"]
    saved = 0
    for item in reversed(recent):  # oldest first so the newest duplicate wins
        submission_id = str(item["submissionId"])
        if not args.force and submission_id in manifest:
            continue
        detail = graphql(DETAIL_QUERY, {"submissionId": int(submission_id)})["submissionDetails"]
        if not detail or not detail.get("code"):
            continue
        path = write_solution(detail)
        manifest[submission_id] = int(detail["timestamp"])
        print(f"saved {path.relative_to(ROOT)}")
        saved += 1
    PROBLEMS_DIR.mkdir(exist_ok=True)
    MANIFEST.write_text(json.dumps(manifest, indent=2, sort_keys=True) + "\n")
    print(f"Sync complete: {saved} submission(s) saved.")


def run_git(*args: str) -> None:
    try:
        subprocess.run(["git", *args], cwd=ROOT, check=True)
    except FileNotFoundError:
        fail("Git is not installed")
    except subprocess.CalledProcessError:
        raise SystemExit(1)


def push(args: argparse.Namespace) -> None:
    if not (ROOT / ".git").exists():
        fail("this folder is not a Git repository; follow README setup first")
    run_git("add", "leetcode")
    changed = subprocess.run(["git", "diff", "--cached", "--quiet"], cwd=ROOT).returncode != 0
    if not changed:
        print("Nothing new to push.")
        return
    run_git("commit", "-m", args.message)
    run_git("push", "-u", "origin", "HEAD")


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__)
    commands = parser.add_subparsers(dest="command", required=True)
    sync_parser = commands.add_parser("sync", help="download recent accepted submissions")
    sync_parser.add_argument("--username", required=True, help="your LeetCode username")
    sync_parser.add_argument("--limit", type=int, default=50, help="recent accepted submissions to inspect (default: 50)")
    sync_parser.add_argument("--force", action="store_true", help="re-download already synced submissions")
    sync_parser.set_defaults(func=sync)
    push_parser = commands.add_parser("push", help="commit and push saved solutions")
    push_parser.add_argument("--message", default="Sync LeetCode solutions", help="Git commit message")
    push_parser.set_defaults(func=push)
    args = parser.parse_args()
    args.func(args)


if __name__ == "__main__":
    main()
