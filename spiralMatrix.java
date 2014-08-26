/*
Given a matrix of m x n elements (m rows, n columns), return all elements of 
the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
*/
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        int m = matrix.length;
        if (m == 0) return result;
        int n = matrix[0].length;
        if (n == 0) return result;

        int row = 0;
        int col = 0;
        while (result.size() < m * n) {
            // left to right
            for (int i = col; i <= n - col - 1; i++)
                result.add(matrix[row][i]);
            if (result.size() == m * n) break;
            // up to down
            for (int j = row + 1; j < m - row - 1; j++)
                result.add(matrix[j][n - col - 1]);
            if (result.size() == m * n) break;
            // right to left
            for (int i = n - col - 1; i >= col; i--)
                result.add(matrix[m - row - 1][i]);
            if (result.size() == m * n) break;
            // down to up
            for (int j = m - row - 2; j > row; j--)
                result.add(matrix[j][col]);

            row++;
            col++;
        }

        return result;
    }
}