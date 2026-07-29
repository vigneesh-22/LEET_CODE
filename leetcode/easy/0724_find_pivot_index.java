// LeetCode 724: Find Pivot Index
// Difficulty: Easy | Language: java | Accepted: 2026-07-29T09:36:56+00:00
// https://leetcode.com/problems/find-pivot-index/
//

class Solution {
    public int pivotIndex(int[] nums) {
        int sum=0;
        int l=0;
        for(int i=0;i<=nums.length-1;i++)
        {
            sum=sum+nums[i];
        }
        int r=sum-nums[0];
        int j=0;
        while(l!=r)
        {   
            j++;
            if(j >= nums.length){
                return -1;
            }
            r=r-nums[j];
            l=sum-r-nums[j];
        }
        return j;
    }
}
