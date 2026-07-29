// LeetCode 217: Contains Duplicate
// Difficulty: Easy | Language: java | Accepted: 2026-07-29T08:18:37+00:00
// https://leetcode.com/problems/contains-duplicate/
//

class Solution {
    public boolean containsDuplicate(int[] nums) 
        {
            Arrays.sort(nums);
        for(int i=0;i<nums.length-1;i++)
        {
            if(nums[i]==nums[i+1])
             {   return true;
        }
        }
        return false;
    }
}
