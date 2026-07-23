# LeetCode local sync

Downloads accepted LeetCode submissions into this structure and pushes it to GitHub:

```
leetcode/
  easy/
  medium/
  hard/
```

## One-time setup

1. Create a blank GitHub repository named `LEET_CODE` (do not add a README there).
2. In this directory, initialise and connect Git. Replace `YOUR_GITHUB_USERNAME`:

   ```bash
   git init
   git branch -M main
   git remote add origin git@github.com:YOUR_GITHUB_USERNAME/LEET_CODE.git
   ```

   If you use HTTPS instead of SSH, use
   `https://github.com/YOUR_GITHUB_USERNAME/LEET_CODE.git` as the remote URL.

3. In a browser, sign in to LeetCode, open Developer Tools → **Application** → **Cookies** → `https://leetcode.com`, then copy the values of `LEETCODE_SESSION` and (if present) `csrftoken`.

   Export them in the terminal. Never put these values in Git or the README:

   ```bash
   export LEETCODE_SESSION='paste-session-value-here'
   export LEETCODE_CSRFTOKEN='paste-csrftoken-value-here'
   ```

   Add the two exports to a private shell profile or password manager workflow if you want them available in later terminals.

## Daily use

After an accepted LeetCode submission, run:

```bash
python3 leetcode_sync.py sync --username YOUR_LEETCODE_USERNAME
python3 leetcode_sync.py push
```

The sync tracks submission IDs, so it only downloads new accepted submissions. Use `--limit 200` on the first import if you have a longer history. It keeps the latest accepted submission for a problem in the appropriate difficulty folder.

To run both in one command:

```bash
python3 leetcode_sync.py sync --username YOUR_LEETCODE_USERNAME && python3 leetcode_sync.py push
```

If LeetCode says authentication failed, copy fresh cookie values from the browser and export them again.
