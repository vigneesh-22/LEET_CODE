// LeetCode 202: Happy Number
// Difficulty: Easy | Language: java | Accepted: 2026-07-28T14:16:28+00:00
// https://leetcode.com/problems/happy-number/
//

class Solution {
    public boolean isHappy(int n) {
     while(n!=1 && n!=4)
     {
         int sum=0;
        while(n>0)
        {
            int rem=n%10;
            sum+=(rem*rem);
            n/=10;
        }
        n=sum;
     }
     return n==1;
}
}
