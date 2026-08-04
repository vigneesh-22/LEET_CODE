// LeetCode 136: Single Number
// Difficulty: Easy | Language: java | Accepted: 2026-08-04T08:18:18+00:00
// https://leetcode.com/problems/single-number/
//

class Solution {
    public int singleNumber(int[] nums) {
      
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

 
    }
