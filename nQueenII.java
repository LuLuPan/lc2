/*
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.
Solution: DFS
          1. Array to keep the col in each row, and scan from top to bot
             new line will compare with each line's A[row] to avoid in the same
             diagonal. This comparsion increases complexity.
          2. Classical solution: three array to check main diagonal, vice diagonal,
             each col, since row is incremental no need to check
*/
public class Solution {
    public int totalNQueens(int n) {
        if (n == 0 || n == 2 || n == 3) return 0;
        // main diagonal
        boolean[] rd = new boolean[2 * n - 1];
        // vice digonal
        boolean[] ld = new boolean[2 * n - 1];
        boolean[] cols = new boolean[n];
        int[] result = new int[1];
        
        helper(n, 0, rd, ld, cols, result);
        return result[0];
    }
    
    private void helper(int n, int row, boolean[] rd, boolean[] ld, boolean[] cols, int[] result) {
        if (row == n) {
            result[0]++;
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (rd[row + n - 1 - i] == true || ld[row + i] == true || cols[i] == true)
                continue;
            rd[row + n - 1 - i] = true;
            ld[row + i] = true;
            cols[i] = true;
            helper(n, row + 1, rd, ld, cols, result);
            cols[i] = false;
            ld[row + i] = false;
            rd[row + n - 1 - i] = false;
        }
    }
}