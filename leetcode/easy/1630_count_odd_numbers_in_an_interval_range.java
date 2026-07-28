// LeetCode 1630: Count Odd Numbers in an Interval Range
// Difficulty: Easy | Language: java | Accepted: 2026-07-28T17:05:49+00:00
// https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/
//

class Solution {
    int countOdds(int low, int high) {
        return(((high+1)/2)-(low/2));
    }
};
