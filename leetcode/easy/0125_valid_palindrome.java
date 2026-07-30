// LeetCode 125: Valid Palindrome
// Difficulty: Easy | Language: java | Accepted: 2026-07-30T06:18:03+00:00
// https://leetcode.com/problems/valid-palindrome/
//

class Solution {
    public boolean isPalindrome(String s) {
        String clean = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String c = new StringBuilder(clean).reverse().toString();
        if(c.equals(clean)) {
            return true;
        } else {
            return false;
        }
    }
}
