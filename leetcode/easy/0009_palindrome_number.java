// LeetCode 9: Palindrome Number
// Difficulty: Easy | Language: java | Accepted: 2026-01-30T14:06:29+00:00
// https://leetcode.com/problems/palindrome-number/
//

class Solution {
    public boolean isPalindrome(int x)  
    {
        int temp = x;
        int rev =0;
        while(x>0)
        {      
            int rem = x % 10;
            rev = rev *10 + rem;
            x = x/10; 
                }  
        if(temp==rev)
        {
            return true ;
        }
        else 
        {
            return false ;
        }
    }                                             
}
