/*
Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' 
placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
*/
public class Solution {
    public List<String[]> solveNQueens(int n) {
        List<String[]> result = new ArrayList<String[]>();
        if (n == 0 || n == 2 || n == 3) return result;
        boolean[] rd = new boolean[2 * n - 1];
        boolean[] ld = new boolean[2 * n - 1];
        boolean[] cols = new boolean[n];
        int[] pos = new int[n];
        Arrays.fill(pos, -1);
        helper(n, 0, rd, ld, cols, pos, result);
        return result;
    }
    
    private void helper(int n, int row, boolean[] rd, boolean[] ld, boolean[] cols, 
                        int[] pos, List<String[]> result) {
        if (row == n) {
            char[] array = new char[n];
            Arrays.fill(array, '.');
            String line = new String(array);
            String[] strs = new String[n];
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder(line);
                sb.setCharAt(pos[i], 'Q');
                strs[i] = sb.toString();
            }
            result.add(strs);
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (rd[row + n - 1 - i] == true || ld[row + i] == true || cols[i] == true)
                continue;
            rd[row + n - 1 - i] = true;
            ld[row + i] = true;
            cols[i] = true;
            pos[row] = i;
            helper(n, row + 1, rd, ld, cols, pos, result);
            pos[row] = -1;
            cols[i] = false;
            ld[row + i] = false;
            rd[row + n - 1 - i] = false;
        }
    }
}