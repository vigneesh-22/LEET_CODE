// LeetCode 191: Number of 1 Bits
// Difficulty: Easy | Language: java | Accepted: 2026-07-28T09:19:24+00:00
// https://leetcode.com/problems/number-of-1-bits/
//

class Solution {
    public int hammingWeight(int n) {
        int b=Integer.bitCount(n);
       return b;
    }
}
