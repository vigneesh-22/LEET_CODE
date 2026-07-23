// LeetCode 231: Power of Two
// Difficulty: Easy | Language: java | Accepted: 2026-02-02T15:22:44+00:00
// https://leetcode.com/problems/power-of-two/
//

class Solution {
    public boolean isPowerOfTwo(int n) {
     if(n>0 &&(n&(n-1))==0)  
        return true;
     else 
        return false;
    }
}
