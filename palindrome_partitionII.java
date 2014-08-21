/*
Given a string s, partition s such that every substring of the partition is 
a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced 
using 1 cut.

Solution: DP to calculate max subregion mincut and DP to record if [i, j]
          substring is palindrome
          DP1: f(i, j) = min(f(i, k), f(k+1, j)) => 1D
          f[i] the mincut from i to n-1, f(i) = min(f(i), f(j+1)+1)
          j is from i to n-1
Complexity: O(n^2) + O(n^2)
*/

public class Solution {
    public int minCut(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] f = new int[n + 1];
        // initiation is the max value for f[i]
        for (int i = 0; i <= n; i++)
            f[i] = n - 1 - i;

        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    f[i] = Math.min(f[i], f[j + 1] + 1);
                }
            }
        }

        return f[0];
    }
}