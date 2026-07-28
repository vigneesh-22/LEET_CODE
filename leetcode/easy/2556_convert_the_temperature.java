// LeetCode 2556: Convert the Temperature
// Difficulty: Easy | Language: java | Accepted: 2026-07-28T06:26:45+00:00
// https://leetcode.com/problems/convert-the-temperature/
//

class Solution {
    public double[] convertTemperature(double celsius) {
       double k=celsius+273.15;
       double f=(celsius*1.8)+32;
       double [] arr= {k,f};
       return arr;
    }
}
