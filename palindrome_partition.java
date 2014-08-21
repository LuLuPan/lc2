/*
Given a string s, partition s such that every substring of the 
partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]

Solution: 1. DP to find all valid palindrome substring
          2. DFS to find all adjacent valid palindrome for the string
             Notice: How to set index

Complexity:

Corner case:
*/
public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        int n = s.length();
        if (n == 0) return result;

        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                // Error: since dp[i][j] depends on dp[i+1][j-1]
                // j change from i..n-1 since j should be always bigger than i
                // need to make scan direction calculate dp[i+1][j-1] at first
                // so need i from n-1..0
                if ((s.charAt(i) == s.charAt(j)) && ((j - i < 2) || dp[i + 1][j - 1]))
                    dp[i][j] = true;
            }
        }
        List<String> path = new ArrayList<String>();
        dfs(s, dp, n - 1, path, result);
        return result;        
    }

    private void dfs(String s, boolean[][] dp, int cur, List<String> path, 
        List<List<String>> result)
    {
        if (cur == -1) {
            List<String> tmp = new ArrayList<String>(path);
            Collections.reverse(tmp);
            result.add(tmp);
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if (dp[i][cur] == true) {
                path.add(s.substring(i, cur + 1));
                dfs(s, dp, i - 1, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
}