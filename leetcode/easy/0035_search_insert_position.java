// LeetCode 35: Search Insert Position
// Difficulty: Easy | Language: java | Accepted: 2026-07-27T08:35:16+00:00
// https://leetcode.com/problems/search-insert-position/
//

class Solution {
    public int searchInsert(int[] nums, int target) {
        int l=0;
        int r=nums.length-1;
        while(l<=r)
        {
            int index=l+(r-l)/2;
            if(nums[index]==target)
            {
                return index;
            }
            else if(nums[index]>target)
            {
                r=index-1;
            }
            else{
                l=index+1;
            }
        }
        return l;

    }
}
