/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.

Solution: DFS

*/
public class Solution {
    public void solveSudoku(char[][] board) {
        if (board.length != 9 || board[0].length != 9) return;
        solvingSudoku(board);
    }

    private boolean solvingSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (int k = 0; k < 9; k++) {
                        board[i][j] = (char)(k + '1');
                        if (isValid(board, i, j) && solvingSudoku(board))
                            return true;
                        board[i][j] = '.';
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int x, int y) {
        // check row
        for (int i = 0; i < 9; i++) {
            if ((board[x][i] == board[x][y]) && (i != y))
                return false;
        }
        // check col
        for (int i = 0; i < 9; i++) {
            if ((board[i][y] == board[x][y]) && (i != x))
                return false;
        }

        // check blocks
        for (int r = (x / 3) * 3; r < (x / 3) * 3 + 3; r++) {
            for (int c = (y / 3) * 3; c < (y / 3) * 3 + 3; c++) {
                if ((board[r][c] == board[x][y]) && (r != x || c != y))
                    return false;
            }
        }

        return true;
    }
}