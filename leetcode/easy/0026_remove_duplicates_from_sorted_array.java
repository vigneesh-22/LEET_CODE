// LeetCode 26: Remove Duplicates from Sorted Array
// Difficulty: Easy | Language: java | Accepted: 2026-08-03T16:53:39+00:00
// https://leetcode.com/problems/remove-duplicates-from-sorted-array/
//

class Solution {
    public int removeDuplicates(int[] nums) {
       int i=0;
       for(int j=1;j<nums.length;j++)
       {
            if(nums[i]!=nums[j])
            {
                i++;
                nums[i]=nums[j];
            }
       } 
       return i+1;
    }
}
