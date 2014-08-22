/*
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number 
of distinct solutions.

Solution: Same as Queen I

*/

public class Solution {
    public int totalNQueens(int n) {
        if (n == 0 || n == 2 || n == 3) return 0;

        boolean[] left_diag = new boolean[2 * n];
        boolean[] right_diag = new boolean[2 * n];
        boolean[] cols = new boolean[n];
        int[] C = new int[n];
        Arrays.fill(C, -1);
        int[] result = new int[1];

        dfs(0, C, left_diag, right_diag, cols, result);
        return result[0];
    }

    private void dfs(int row, int[] C, boolean[] left_diag, boolean[] right_diag,
        boolean[] cols, int[] result)
    {
        if (row == C.length) {
            result[0]++;
            return;
        }

        // for each col
        for (int i = 0; i < C.length; i++) {
            if (cols[i] == true || left_diag[row + i] == true ||
                right_diag[row - i + C.length] == true)
                continue;
            cols[i] = true;
            left_diag[row + i] = true;
            right_diag[row - i + C.length] = true;
            C[row] = i;
            dfs(row + 1, C, left_diag, right_diag, cols, result);
            cols[i] = false;
            left_diag[row + i] = false;
            right_diag[row - i + C.length] = false;
            C[row] = -1;
        }
    }
}