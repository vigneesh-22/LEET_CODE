// LeetCode 4107: Find Missing Elements
// Difficulty: Easy | Language: java | Accepted: 2026-08-04T06:43:09+00:00
// https://leetcode.com/problems/find-missing-elements/
//

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> missing = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-1;i++)
        {
            if(nums[i] +1!=nums[i+1])
            {
                int expected = nums[i] + 1;
                while (expected < nums[i + 1])
                 {
                    missing.add(expected);
                    expected++;
                }
            }
            }
            return missing;
    }

}
