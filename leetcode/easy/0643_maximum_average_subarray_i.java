// LeetCode 643: Maximum Average Subarray I
// Difficulty: Easy | Language: java | Accepted: 2026-07-27T09:14:19+00:00
// https://leetcode.com/problems/maximum-average-subarray-i/
//

class Solution {
    public double findMaxAverage(int[] nums, int k) {
    
        int sum=0;
        for(int i=0;i<k;i++)
        {   
            sum=sum+nums[i];         
        }
        int num=sum;
        for (int i=k;i<k;i++)
        {
            sum=sum+nums[i];
        }
        int max=sum;
        for (int i=k;i<nums.length;i++)
        {
            sum =sum-nums[i-k]+nums[i];
            if(sum>max)
            {
                max=sum;
            }
        }
        return (double)max/k;
    }
    
}
