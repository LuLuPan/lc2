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
            for (int j = 0; j < 9; j++) {
                if (!check(i, j, used, board))
                    return false;
            }

            Arrays.fill(used, false);
            for (int j = 0; j < 9; j++) {
                if (!check(j, i, used, board))
                    return false;
            }
        }

        
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                Arrays.fill(used, false);
                for (int i = 3 * r; i < 3 * r + 3; i++) {
                    for (int j = 3 * c; j < 3 * c + 3; j++) {
                        if (!check(j, i, used, board))
                            return false;
                    }
                }        
            }
        }
        return true;
    }

    private boolean check(int y, int x, boolean[] used, char[][] board) {
        if (board[y][x] == '.') return true;
        int num = board[y][x] - '1';
        if (used[num] == true) return false;
        return used[num] = true;
    }
}