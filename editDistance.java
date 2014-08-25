/*
Given two words word1 and word2, find the minimum number of steps required
to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character

Solution: str1c and str2d
          f[i][j] means min step from str1[0..i] to str2[0..j]
          c == d : f[i][j] = f[i - 1][j - 1]
          c != d : f[i][j] = min {f[i - 1][j - 1] + 1 (replace)
                                  f[i][j - 1] + 1 (insert d after c)
                                  f[j - 1][i] + 1 (delete c) }
          Similar to Min Path Sum in a m x n matrix since each operation
          counts as one step.
          But each point could come from three directions
*/
public class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        if (m == 0 && n == 0) return 0;

        // n characters will have n + 1 intervals
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
            f[i][0] = i;

        for (int j = 0; j <= n; j++)
            f[0][j] = j;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    f[i][j] = f[i - 1][j - 1];
                else {
                    int temp = Math.min(f[i - 1][j - 1], f[i][j - 1]);
                    f[i][j] = Math.min(f[i - 1][j], temp) + 1;
                }
            }
        }

        return f[m][n];
    }
}