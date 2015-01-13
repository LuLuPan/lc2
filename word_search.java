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

        // visited[][] to mark used cell to avoid repeating usage
        // Notice: To save space, if board is not immutable, 
        // mark the cell as '#' as true, recover after access

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

        // if (board[y][x] == '#')
        if (visited[y][x] == true) return false;

        if (word.charAt(step) != board[y][x])
            return false;
        // char c = board[y][x]
        // board[y][x] = '#'
        visited[y][x] = true;
        boolean ret = dfs(board, word, y, x - 1, step + 1, visited) ||
               dfs(board, word, y, x + 1, step + 1, visited) ||
               dfs(board, word, y - 1, x, step + 1, visited) ||
               dfs(board, word, y + 1, x, step + 1, visited);
        // board[y][x] = c
        visited[y][x] = false;
        return ret;
    }
}


public class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0)
            return false;
        int m = board.length;
        int n = board[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (helper(board, i, j, word, 0))
                        return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean helper(char[][] board, int y, int x, String word, int steps) {
        if (steps == word.length()) return true;
        if (y < 0 || y >= board.length || x < 0 || x >= board[0].length) return false;
        if (board[y][x] == '#') return false;
        if (board[y][x] != word.charAt(steps)) return false;
        board[y][x] = '#';
        boolean res = helper(board, y, x + 1, word, steps + 1) ||
                      helper(board, y, x - 1, word, steps + 1) ||
                      helper(board, y + 1, x, word, steps + 1) ||
                      helper(board, y - 1, x, word, steps + 1);
        board[y][x] = word.charAt(steps);
        return res;
    }
}