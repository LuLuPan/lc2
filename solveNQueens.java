/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard 
such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' 
placement, where 'Q' and '.' both indicate a queen and an empty space 
respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]

Solution: DFS
          1. Array to keep the col in each row, and scan from top to bot
             new line will compare with each line's A[row] to avoid in the same
             diagonal. This comparsion increases complexity.
          2. Classical solution. left and right diagonal vector.
             For left diag, row + col is the same for points on same diag
             For right diga, row - col is the same for points on same diag
             Use 2*n - 1 position to record the usage of each diag.
             Use cols[n] to record col is used or not
             Use c[n] to record col for each row

*/
public class Solution {
    public List<String[]> solveNQueens(int n) {
        List<String[]> result = new ArrayList<String[]>();
        if (n == 0 || n == 2 || n == 3) return result;
        int[] A = new int[n];
        // cannot use 0 as init since its valid
        Arrays.fill(A, -1);
        dfs(n, 0, A, result);
        return result;
    }

    private boolean isValid(int row, int col, int[] A) {
        for (int i = row - 1; i >= 0; i--) {
            if (A[i] == col || Math.abs(col - A[i]) == row - i)
                return false;
        }

        return true;
    }

    private void dfs(int n, int row, int[] A, List<String[]> result) {
        if (row == n) {
            // format result
            char[] tmp = new char[n];
            Arrays.fill(tmp, '.');
            String line = new String(tmp);
            String[] sol = new String[n];

            for (int i = 0; i < n; i++) {
                StringBuilder new_line = new StringBuilder(line);
                new_line.setCharAt(A[i], 'Q');
                sol[i] = new_line.toString();
            }
            result.add(sol);
            return;
        }

        for (int i = 0; i < n; i++) {
            // for each col
            if (isValid(row, i, A)) {
                A[row] = i;
                dfs(n, row + 1, A, result);
                A[row] = -1;
            }
        }
    }
}


public class Solution {
    public List<String[]> solveNQueens(int n) {
        List<String[]> result = new ArrayList<String[]>();
        if (n == 0 || n == 2 || n == 3) return result;
        boolean[] left_diag = new boolean[2 * n];
        boolean[] right_diag = new boolean[2 * n];
        boolean[] cols = new boolean[n];
        int[] C = new int[n];
        Arrays.fill(left_diag, false);
        Arrays.fill(right_diag, false);
        Arrays.fill(cols, false);
        Arrays.fill(C, -1);

        dfs(0, C, left_diag, right_diag, cols, result);
        return result;
    }

    private void dfs(int row, int[] C, boolean[] left_diag, boolean[] right_diag,
        boolean[] cols,
        List<String[]> result)
    {
        if (row == C.length) {
            // format result
            char[] char_arr = new char[C.length];
            Arrays.fill(char_arr, '.');
            String line = new String(char_arr);
            String[] sol = new String[C.length];
            for (int i = 0; i < C.length; i++) {
                StringBuilder line_sb = new StringBuilder(line);
                line_sb.setCharAt(C[i], 'Q');
                sol[i] = line_sb.toString();
            }
            result.add(sol);
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
            // recover
            cols[i] = false;
            left_diag[row + i] = false;
            right_diag[row - i + C.length] = false;
            C[row] = -1;
        }

    }
}
