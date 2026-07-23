// LeetCode 172: Factorial Trailing Zeroes
// Difficulty: Medium | Language: java | Accepted: 2026-07-21T17:00:26+00:00
// https://leetcode.com/problems/factorial-trailing-zeroes/
//

class Solution {
    public int trailingZeroes(int n) {
        int c=0;
        while(n>=5)
        {
        c=c+(n/5);
        n=n/5;
        }
        return c;
    }
}
