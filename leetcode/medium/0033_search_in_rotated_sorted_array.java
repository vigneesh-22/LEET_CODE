// LeetCode 33: Search in Rotated Sorted Array
// Difficulty: Medium | Language: java | Accepted: 2026-07-29T06:49:33+00:00
// https://leetcode.com/problems/search-in-rotated-sorted-array/
//

class Solution {
    public int search(int[] nums, int target) {
        int l=0;
        int r=nums.length-1;
        while(l<=r)
        {
            int mid=(l+(r-l)/2);
            if(nums[mid]==target)
            {
                return mid;
            }
            if(nums[l]<=nums[mid]){
               if(nums[l]<=target & nums[mid] > target){
                r=mid-1;
               }
               else{
                l=mid+1;
               }
            }
            else{
               if(nums[r]>=target & nums[mid] < target){
                l=mid+1;
               }
               else{
                r=mid-1;
               }
            }
        }
        return -1;
    }
}
