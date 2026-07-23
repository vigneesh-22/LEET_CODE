// LeetCode 1: Two Sum
// Difficulty: Easy | Language: java | Accepted: 2026-01-30T13:44:56+00:00
// https://leetcode.com/problems/two-sum/
//

class Solution {
    public int[] twoSum(int[] nums, int target) {
        for(int i =0 ;i<nums.length;i++)
        {
            for(int j=i+1;j<nums.length;j++)
            {
                if((nums[i]+nums[j])==target)
                {
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{};
        
    }
}
