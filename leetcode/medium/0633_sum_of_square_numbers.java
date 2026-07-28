// LeetCode 633: Sum of Square Numbers
// Difficulty: Medium | Language: java | Accepted: 2026-07-28T17:16:43+00:00
// https://leetcode.com/problems/sum-of-square-numbers/
//

class Solution {
    public boolean judgeSquareSum(int n) {
        long l = 0;
        long r = (long) Math.sqrt(n);
        while (l <= r) {
            long s = (l * l) + (r * r);
            
            if (s == n) {
                return true;
            } else if (s < n) {
                l++;
            } else {
                r--;
            }
        }
        return false;
    }
}
