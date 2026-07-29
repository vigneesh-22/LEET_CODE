// LeetCode 53: Maximum Subarray
// Difficulty: Medium | Language: java | Accepted: 2026-07-29T10:28:53+00:00
// https://leetcode.com/problems/maximum-subarray/
//

class Solution {
    public int maxSubArray(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int cu_sm = nums[0];
        int max_sm = nums[0];
        for(int i=1 ; i<nums.length ; i++){
            cu_sm = Math.max(cu_sm+nums[i],nums[i]);
            max_sm = Math.max(max_sm,cu_sm);
        }
        return max_sm;
    }
}
