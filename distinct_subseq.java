/*
Given a string S and a string T, count the number of distinct subsequences
of T in S.

A subsequence of a string is a new string which is formed from the original 
string by deleting some (can be none) of the characters without disturbing the
relative positions of the remaining characters. (ie, "ACE" is a subsequence 
of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.

Note: subsequence in S should be same as T
Soltion: DP
dp[0][0] = 1. T and S are empty
dp[i][0] = 0.  S is empty
dp[0][i] = 1, T is empty
if[i][j-1] already matched, count is at least dp[i][j-1]
Try to match T[i-1] and S[j-1]
dp[i][j] = dp[i][j - 1] + (T[i-1] == S[j-1] ? dp[i-1][j-1] : 0)

*/
public class Solution {
    public int numDistinct(String S, String T) {
        int n = S.length();
        int m = T.length();

        // +1 to include empty string
        // S is empty
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }

        // T is empty
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j - 1];
                if (T.charAt(i - 1) == S.charAt(j - 1))
                    dp[i][j] += dp[i - 1][j - 1];
            }
        }

        return dp[m][n];
    }
}