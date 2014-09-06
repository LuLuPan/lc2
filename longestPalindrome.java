/*
Given a string S, find the longest palindromic substring in S. 
You may assume that the maximum length of S is 1000, and there exists one 
unique longest palindromic substring.
*/
public class Solution {
    public String longestPalindrome(String s) {
        if (s.length() <= 1) return s;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int maxLen = 1;

        dp[0][0] = true;
        int left = 0;
        int right = 0;
        for (int i = 0; i < n; i++) {
        	dp[i][i] = true;
        	for (int j = 0; j < i; j++) {
        	    // dp[i + 1][j - 1] should be calculated before dp[i][j]
        		if (((i - j) < 2 && s.charAt(i) == s.charAt(j)) ||
        			(dp[i - 1][j + 1] && s.charAt(i) == s.charAt(j))) {
        			dp[i][j] = true;
                    if (i - j + 1 > maxLen) {
                    	maxLen = i - j + 1;
                        left = j;
                        right = i;
                    }
        		}
         	}
        }

        return s.substring(left, right + 1);
    }
}