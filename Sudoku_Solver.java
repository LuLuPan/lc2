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
                        if (check(board, i, j) && solvingSudoku(board))
                            return true;
                    }
                    board[i][j] = '.';
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean check(char[][] board, int y, int x) {
        for (int i = 0; i < 9; i++) {
            if (board[i][x] == board[y][x] && i != y)
                return false;
        }
        
        for (int i = 0; i < 9; i++) {
            if (board[y][i] == board[y][x] && i != x)
                return false;
        }
        
        for (int i = (y / 3) * 3; i < (y / 3) * 3 + 3; i++) {
            for (int j = (x / 3) * 3; j < (x / 3) * 3 + 3; j++) {
                if (board[i][j] == board[y][x] && (i != y || j != x))
                    return false;
            }
        }
        
        return true;
    }
}