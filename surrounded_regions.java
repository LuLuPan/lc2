/*
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Solution: Other than find 'O', mark 'O' on edge as 'E' and BFS to search all
'O' connect with 'E' and mark them as 'E' too.
Then make 'O' left as 'X' and recover 'E' to 'O'

Complexity: O(n) + O(n)
*/

public class Solution {
    public void solve(char[][] board) {
        int m = board.length;
        if (m == 0) return;
        int n = board[0].length;
        if (n == 0) return;

        Queue<Integer> onedge_queue = new LinkedList<Integer>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1)
                    //Error: shouldn't change to 'E' here
                    //board[i][j] = board[i][j] == 'O' ? 'E' : board[i][j];
                    if (board[i][j] == 'O')
                        onedge_queue.add(i * n + j);
            }
        }

        while (!onedge_queue.isEmpty()) {
            int pos = onedge_queue.poll();
            int y = pos / n;
            int x = pos % n;
            if (board[y][x] == 'E')
                continue;

            board[y][x] = 'E';

            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if ((i + j) != 0 && (i == 0 || j == 0)) {
                        if (((y + i) >= 0 && (y + i) <= m - 1) &&
                            ((x + j) >= 0 && (x + j) <= n - 1))
                            if (board[y + i][x + j] == 'O')
                                queue.add((y + i) * n + x + j);
                    }
                }
            }
        }

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == 'E')
                    board[i][j] = 'O';
    }
}