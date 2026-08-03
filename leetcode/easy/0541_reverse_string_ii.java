// LeetCode 541: Reverse String II
// Difficulty: Easy | Language: java | Accepted: 2026-07-31T06:28:20+00:00
// https://leetcode.com/problems/reverse-string-ii/
//

class Solution {
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();        
        for (int i = 0; i < arr.length; i += 2 * k) {
            int start = i;
            int end = Math.min(i + k - 1, arr.length - 1);
            while (start < end) {
                char temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
                end--;
            }
        }
        return new String(arr);
    }
}
