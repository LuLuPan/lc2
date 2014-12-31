/*
Given a string s1, we may represent it as a binary tree by partitioning it to 
two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two
children.

For example, if we choose the node "gr" and swap its two children, it produces 
a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it 
produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled 
string of s1.

Solution: f[n][i][j] length is n, and s1[i] start from i and s2[j] starts from
          j, is scramble or not

          s1: |--k--|--n-k--|
              i
          s2: |--k--|--n-k--|
              j
    or    s2: |--n-k--|--k--|

    f[n][i][j] = (f[k][i][j] && f[n-k][i + k][j + k]) ||
                 (f[k][i][j + n - k] && f[n-k][i + k][j])

    Target: f[n][0][0]
*/
public class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        int len = s1.length();
        if (len == 0) return false;
        // length change from 0 to n, so n + 1 slot
        boolean[][][] f = new boolean[len + 1][len][len];
        f[0][0][0] = false;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                f[1][i][j] = s1.charAt(i) == s2.charAt(j);
            }
        }

        for (int n = 1; n <= len; n++) {
            for (int i = 0; i + n <= len; i++) {
                for (int j = 0; j + n <= len; j++) {
                    for (int k = 1; k < n; k++) {
                        f[n][i][j] = (f[k][i][j] && f[n - k][i + k][j + k]) ||
                            (f[k][i][j + n - k] && f[n - k][i + k][j]);
                        // Notice: break when true, otherwise false may overwrite 
                        // possible case
                        if (f[n][i][j] == true)
                            break;
                    }
                }
            }
        }

        return f[len][0][0];
    }
}