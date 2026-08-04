// LeetCode 151: Reverse Words in a String
// Difficulty: Medium | Language: java | Accepted: 2026-08-04T05:32:24+00:00
// https://leetcode.com/problems/reverse-words-in-a-string/
//

class Solution {
    public String reverseWords(String s) {
        String [] arr=s.trim().split("\\s+");
        String result = "";
        for(int i=arr.length-1;i>0;i--)
        {
            result += arr[i]+" ";
        }
        result += arr[0];
        return result;
    }
}
