/*
Given a string S, find the longest palindromic substring in S. 
You may assume that the maximum length of S is 1000, and there exists one 
unique longest palindromic substring.
*/
public class Solution {
    // DP
    // f[j, i]: 1) true if i == j
    //          2) true if s[i] == s[j], i - j < 2
    //          3) true if s[i] == s[j] && f(j+1, i-1), i - j >= 2
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0 || n == 1) return s;
        String result = new String();

        boolean[][] dp = new boolean[n][n];
        
        int start = -1;
        int maxLen = 1;
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            for (int j = i - 1; j >= 0; j--) {
                if (s.charAt(j) == s.charAt(i) && (j == i - 1 || 
                    (i - j >= 2 && dp[j + 1][i - 1] == true))) {
                    dp[j][i] = true;
                    if (i - j + 1 > maxLen) {
                        maxLen = i - j + 1;
                        start = j;
                    }
                }
            }
        }
        
        return s.substring(start, start + maxLen);
    }
}

public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int n = s.length();
        boolean[][] f = new boolean[n][n];
        int maxLen = 1;
        int start = 0;
        // [i+1][j-1]: i should be decrementing since i will use i+1, 
        //             j should be incrementing since j will use j-1
        for (int i = n - 1; i >= 0; i--) {
            f[i][i] = true;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || f[i + 1][j - 1])) {
                    f[i][j] = true;
                    if (j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        start = i;
                    }
                }
            }
        }
        
        return s.substring(start, start + maxLen);
    }
}

// Solution 2
// Manacher’s Algorithm
// http://leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
