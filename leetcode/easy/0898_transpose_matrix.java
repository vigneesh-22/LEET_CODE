// LeetCode 898: Transpose Matrix
// Difficulty: Easy | Language: java | Accepted: 2026-07-29T08:52:28+00:00
// https://leetcode.com/problems/transpose-matrix/
//

class Solution {
    public int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }
}
