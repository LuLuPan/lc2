/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once.

For example,
Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.

Solution: DFS in four directions
Complexity:

*/

public class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        if (m == 0) return false;
        int n = board[0].length;
        if (n == 0) return false;

        if (m * n < word.length()) return false;

        boolean[][] visited = new boolean[m][n];
        // try each pos as start point
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, i, j, 0, visited) == true)
                    return true;        
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int y, int x, int step,
        boolean[][] visited) {
        if (step == word.length()) {
            return true;
        }

        if (y < 0 || y >= board.length || x < 0 || x >= board[0].length)
            return false;
 
        if (visited[y][x] == true) return false;

        if (word.charAt(step) != board[y][x])
            return false;
        visited[y][x] = true;
        boolean ret = dfs(board, word, y, x - 1, step + 1, visited) ||
               dfs(board, word, y, x + 1, step + 1, visited) ||
               dfs(board, word, y - 1, x, step + 1, visited) ||
               dfs(board, word, y + 1, x, step + 1, visited);
        visited[y][x] = false;
        return ret;
    }
}