/*
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.

Solution: DP
f[i][j] means s1[0..i] and s2[0..j] interleaved into s3[0...i+j]

f[i][j] = (s1[i - 1] == s3[i - 1 + j] && f[i - 1][j]) ||
          (s2[j - 1] == s3[i + j - 1] && f[i][j - 1])


*/
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        if (m + n != s3.length()) return false;
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 1; i <= m; i++)
            f[i][0] = (s1.charAt(i - 1) == s3.charAt(i - 1) && f[i - 1][0]);

        for (int j = 1; j <= n; j++)
            f[0][j] = (s2.charAt(j - 1) == s3.charAt(j - 1) && f[0][j - 1]);

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                f[i][j] = (s1.charAt(i - 1) == s3.charAt(i + j - 1) && f[i - 1][j]) ||
                          (s2.charAt(j - 1) == s3.charAt(i + j - 1) && f[i][j - 1]);
            }
        }

        return f[m][n];
    }
}


// 1D Rolling Array
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        if (m + n != s3.length()) return false;
        
        boolean[] f = new boolean[n + 1];
        f[0] = true;
        
        for (int j = 1; j <= n; j++)
            f[j] = (f[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1));
            
        for (int i = 1; i <= m; i++) {
            f[0] = f[0] && s1.charAt(i - 1) == s3.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                f[j] = (f[j] && s1.charAt(i - 1) == s3.charAt(i - 1 + j)) ||
                       (f[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        
        return f[n];
    }
}