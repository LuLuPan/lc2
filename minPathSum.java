/*
Given a m x n grid filled with non-negative numbers, find a path from top left 
to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Solution: 1. DP: f[i][j] = min{f[i-1][j], f[i][j-1]} + num[i][j]
          2. DFS + Memo
             Both is ok for Start from right-bottom to left-top or
             left-top to right-bottom
*/
public class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        if (n == 0) return 0;

        int[][] f = new int[m][n];

        return dfs(grid, f, 0, 0);
    }

    private int dfs(int[][] grid, int[][] f, int y, int x) {
        int m = grid.length;
        int n = grid[0].length;
        if (y > m - 1 || x > n - 1) return Integer.MAX_VALUE;
         // back to start point, converge
        if (y == m - 1 && x == n - 1) return grid[m - 1][n - 1];
        else return Math.min(getOrUpdate(grid, f, y + 1, x),
                        getOrUpdate(grid, f, y, x + 1)) + grid[y][x];
    }

    private int getOrUpdate(int[][] grid, int[][] f, int y, int x) {
        if (y > grid.length - 1 || x > grid[0].length - 1)
            return Integer.MAX_VALUE;
        if (f[y][x] > 0)
            return f[y][x];
        else return f[y][x] = dfs(grid, f, y, x);
    } 
}

//DP : 2D
public class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] f = new int[m][n];
                
        f[0][0] = grid[0][0];
        for (int i = 1; i < m; i++)
            f[i][0] = f[i - 1][0] + grid[i][0];
    
        for (int j = 1; j < n; j++)
            f[0][j] = f[0][j - 1] + grid[0][j];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + grid[i][j];
            }
        }
        
        return f[m - 1][n - 1];
    }
}

//DP: 1D Rolling Array
public class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        
        int[] f = new int[n];
        // Notice: 
        // Since for first row, f[j] = 0,
        // min(f[j - 1], f[j]) will get f[i] not f[j - 1]
        // so need  f[j] = MAX_VALUE at first
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        
        for (int i = 0; i < m; i++) {
            // F[i][0] = F[i - 1][0] + Grid[i][0]
            f[0] = f[0] + grid[i][0];
            for (int j = 1; j < n; j++) {
                // F[i][j] = Min {F[i][j - 1], F[i - 1][j]} + Grid[i][j]
                f[j] = Math.min(f[j - 1], f[j]) + grid[i][j];
            }
        }
        
        return f[n - 1];
    }
}