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