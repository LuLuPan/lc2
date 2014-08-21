/*
A robot is located at the top-left corner of a m x n grid 
(marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the grid 
(marked 'Finish' in the diagram below).

How many possible unique paths are there?

Solution: 1. DP: f[i][j] = f[i-1][j] + f[i][j-1]
            2D=>1D : f[j] = f[j] + f[j-1]
          2. DFS + Memo
Complexity:
Corner case:
*/
public class Solution {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) return 0;
        int[] f = new int[n];
        f[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[j] = f[j] + f[j - 1];
            }
        }

        return f[n - 1];
    }
}

public class Solution {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) return 0;
        // col 0 and row 0 is not used
        // m+1 and n+1 will make index easy
        // since j-1 and i-1 will be used later
        int[][] f = new int[m + 1][n + 1];
        return dfs(n, m, f);
    }

    private int dfs(int x, int y, int[][] f) {
        if (x < 1 || y < 1) return 0; //invalid cond
        if (x == 1 && y == 1) return 1; // termiate cond

        return getOrUpdate(x - 1, y, f) + getOrUpdate(x, y - 1, f);
    }

    private int  getOrUpdate(int x, int y, int[][] f) {
        if (f[y][x] > 0) return f[y][x];
        else return f[y][x] = dfs(x, y, f);
    }
}