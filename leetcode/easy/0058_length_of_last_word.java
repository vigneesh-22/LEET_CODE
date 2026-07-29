// LeetCode 58: Length of Last Word
// Difficulty: Easy | Language: java | Accepted: 2026-07-29T04:01:55+00:00
// https://leetcode.com/problems/length-of-last-word/
//

class Solution {
    public int lengthOfLastWord(String s) {
        String [] se=s.split("\\s+");
        String lastword=se[se.length-1];
        int a=lastword.length();
        return a;
    }
}
