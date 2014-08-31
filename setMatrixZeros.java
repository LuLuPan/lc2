/*
Given a m x n matrix, if an element is 0, set its entire row and column to 0. 
Do it in place.

click to show follow up.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?

Solution: In order to in place, scan matrix, if [y, x] is 0, mark[y, 0] and
          [0, x] to 0.
          Scan first row and first col, set its row and col to 0 if it has 0.
*/
public class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return;
        int n = matrix[0].length;
        if (n == 0) return;

        // Error: Need to scan first row and col at first
        // to see if they have 0, or after scan matrix, cannot know
        // if they have their own, then first row or col may not be set right
        boolean firstColZero = false;
        for (int i = 0; i < m; i++) {
        	if (firstColZero) break;
        	if (matrix[i][0] == 0)
        		firstColZero = true;
        }

        boolean firstRowZero = false;
        for (int j = 0; j < n; j++) {
        	if (firstRowZero) break;
        	if (matrix[0][j] == 0)
        		firstRowZero = true;
        }

        for (int i = 1; i < m; i++) {
        	for (int j = 1; j < n; j++) {
        		if (matrix[i][j] == 0) {
        			matrix[i][0] = 0;
        			matrix[0][j] = 0;
        		}
        	}
        }

        for (int i = 1; i < m; i++) {
        	if (matrix[i][0] == 0) {
        		for (int j = 1; j < n; j++)
        			matrix[i][j] = 0;
        	}
        }

        for (int j = 1; j < n; j++) {
        	if (matrix[0][j] == 0) {
        		for (int i = 1; i < m; i++)
        			matrix[i][j] = 0;
        	}
        }

        if (firstColZero)
        	for (int i = 0; i < m; i++)
        		matrix[i][0] = 0;
        if (firstRowZero)
        	for (int j = 0; j < n; j++)
        		matrix[0][j] = 0;
    }
}