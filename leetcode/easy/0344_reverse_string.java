// LeetCode 344: Reverse String
// Difficulty: Easy | Language: java | Accepted: 2026-07-27T05:54:10+00:00
// https://leetcode.com/problems/reverse-string/
//

class Solution {
    public void reverseString(char[] s) {
     int f=0;
     int last=s.length -1;
     while(f<last)
     {
        char temp=s[f];
        s[f]=s[last];
        s[last]=temp;
        f++;
        last--;
     }   
    }
}
