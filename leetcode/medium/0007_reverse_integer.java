// LeetCode 7: Reverse Integer
// Difficulty: Medium | Language: java | Accepted: 2026-07-28T16:50:10+00:00
// https://leetcode.com/problems/reverse-integer/
//

class Solution {
    public int reverse(int x) {
        long rem=0;
        while(x!=0)
        {
          int digit=x%10;
          rem =rem*10+digit;
          x/=10;
        }
        if (rem > Integer.MAX_VALUE || rem < Integer.MIN_VALUE)
        {
            return 0;
        }
        return (int)rem;
    }
}
