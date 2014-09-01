/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?

Solution: 1. Swap up and down
          2. Swap Diagonal
*/
public class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n == 0 || n == 1) return;

        // swap up and down
        for (int i = 0; i < n / 2; i++) {
        	for (int j = 0; j < n; j++) {
        		int tmp = matrix[i][j];
        		matrix[i][j] = matrix[n - i - 1][j];
        		matrix[n - i - 1][j] = tmp;
        	}
        }

        // swap dignoal
        for (int i = 0; i < n - 1; i++) {
        	for (int j = i + 1; j < n; j++) {
        		int tmp = matrix[i][j];
        		matrix[i][j] = matrix[j][i];
        		matrix[j][i] = tmp;
        	}
        }
    }
}