/*
Given an integer n, generate a square matrix filled with elements 
from 1 to n^2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/
public class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        if (n == 0) return matrix;

        int count = 1;
        int col = 0;
        int row = 0;
        while (count <= n * n) {
            // left to right
            for (int i = col; i <= n - col - 1; i++)
                matrix[row][i] = count++;
            if (count - 1== n * n) break;

            // top to bot
            for (int j = row + 1; j < n - row - 1; j++)
                matrix[j][n - col - 1] = count++;
            if (count - 1 == n * n) break;

            // right to left
            for (int i = n - col - 1; i >= col; i--)
                matrix[n - row - 1][i] = count++;
            if (count - 1 == n * n) break;

            // bot to top
            for (int j = n - row - 2; j > row; j--)
                matrix[j][col] = count++;

            row++;
            col++;
        }

        return matrix;
    }
}
