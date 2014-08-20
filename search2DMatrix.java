/*
Write an efficient algorithm that searches for a value in an m x n matrix. 
This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the 
previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.

Solution: Binary search to find lower bound
*/

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;
        if (n == 0) return false;

        int start = 0;
        int end = m * n - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (matrix[mid / n][mid % n] < target)
                start = mid + 1;
            else
                end = mid;
        }

        if (matrix[start / n][start % n] != target)
            return false;
        return true;
    }
}