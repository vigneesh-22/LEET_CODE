// LeetCode 2491: Smallest Even Multiple
// Difficulty: Easy | Language: java | Accepted: 2026-07-28T06:13:05+00:00
// https://leetcode.com/problems/smallest-even-multiple/
//

class Solution {
    public int smallestEvenMultiple(int n) {
     if(n%2==0)
     {
        return n;
     }   
     else
     {
        return n*2;
     }
    }
}
