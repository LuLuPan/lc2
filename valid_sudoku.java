/*
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with 
the character '.'.
*/
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        if (board.length != 9 || board[0].length != 9) return false;
        boolean[] used = new boolean[9];
        
        for (int i = 0; i < 9; i++) {
            Arrays.fill(used, false);
            for (int j = 0; j < 9; j++)
                if (!check(i, j, used, board)) return false;
                
            Arrays.fill(used, false);
            for (int j = 0; j < 9; j++)
                if (!check(j, i, used, board)) return false;
        }
        
        for (int r = 0; r < 9; r += 3) {
            for (int c = 0; c < 9; c += 3) {
                Arrays.fill(used, false);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++)
                        if (!check(r + i, c + j, used, board)) return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean check(int y, int x, boolean[] used, char[][] board) {
        if (board[y][x] == '.') return true;
        int i = board[y][x] - '1';
        if (used[i] == true) return false;
        return used[i] = true;
    }
}
