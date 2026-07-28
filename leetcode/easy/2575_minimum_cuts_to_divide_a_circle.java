// LeetCode 2575: Minimum Cuts to Divide a Circle
// Difficulty: Easy | Language: java | Accepted: 2026-07-28T06:43:01+00:00
// https://leetcode.com/problems/minimum-cuts-to-divide-a-circle/
//

class Solution {
    public int numberOfCuts(int n) {
        if(n==1)
        {
            return 0;
        }
        if(n%2==0)
            return n/2;
        else
            return n;
    }
}
