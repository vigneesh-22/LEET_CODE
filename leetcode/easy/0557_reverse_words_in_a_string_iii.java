// LeetCode 557: Reverse Words in a String III
// Difficulty: Easy | Language: java | Accepted: 2026-07-30T08:36:12+00:00
// https://leetcode.com/problems/reverse-words-in-a-string-iii/
//

class Solution {
    public String reverseWords(String s) {
        String []a = s.split(" ");
        String b = ""; 
        for(int i = 0; i < a.length; i++) {
          b += new StringBuilder(a[i]).reverse().toString() + " ";
        }
        
        return b.trim(); 
    }
}
