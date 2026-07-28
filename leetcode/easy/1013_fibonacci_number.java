// LeetCode 1013: Fibonacci Number
// Difficulty: Easy | Language: java | Accepted: 2026-07-28T08:59:48+00:00
// https://leetcode.com/problems/fibonacci-number/
//

class Solution {
    public int fib(int n) {
        if(n==0)
            return 0;
        if(n==1)
            return 1;
       int a=0;
       int b=1;
       for (int i=2;i<=n;i++)
       {
           int c=a+b;
            a=b;
            b=c;
       } 
       return b;
    }
}
