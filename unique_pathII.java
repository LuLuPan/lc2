/*
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. 
How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.

Solution: 1. DP
          2. DFS + Memo
*/
public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m == 0) return 0;
        int n = obstacleGrid[0].length;
        if (n == 0) return 0;

        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1)
            return 0;
        int[][] f = new int[m + 1][n + 1];
        return dfs(m, n, f, obstacleGrid);
    }
    private int dfs(int y, int x, int[][] f, int[][] obstacleGrid) {
        if (y < 1 || x < 1) return 0;
        if (obstacleGrid[y - 1][x - 1] == 1) return 0;
        if (y == 1 && x == 1) return 1;

        return getOrUpdate(y - 1, x, f, obstacleGrid) +
               getOrUpdate(y, x - 1, f, obstacleGrid);
    }

    private int getOrUpdate(int y, int x, int[][] f, int[][] obstacleGrid) {
        if (f[y][x] > 0) return f[y][x];
        else return f[y][x] = dfs(y, x, f, obstacleGrid);
    }
}