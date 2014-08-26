/*
Given a string s and a dictionary of words dict, add spaces in s to construct
a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

Solution: DP same as Word break
Use 2D matrix to track valid range pair (i, j)

Corner case:
DFS start from begin will time out, since too much back-tracing
"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
"a","aa", "aaa","aaaa", "aaaaa", "aaaaaa"

If state matrix is generated from top-left to right-bot
invalid 'b' will show as false at the last of matrix

When DFS start scan from top-left to right-bot, it will encounter true
and keep generating path, but will encounter false at last step, then it will
back-tracing. It will be time out.
So it should scan from right-bot to left-top, in this way, false will be
encountered at the first time and no invalid path will be generated.

If state matrix is generated from bot-right to left-top, false will at start
If DFS scans from bot-right to left-top, same situation will happen.


For DP+DFS to generate paths, DP state transfer direction should be opposite
as DFS scan direction. 

*/
public class Solution {
    public List<String> wordBreak(String s, Set<String> dict) {
        int n = s.length();
        List<String> result = new ArrayList<String>();
        if (dict.size() == 0) return result;
        boolean[] f = new boolean[n + 1];
        // empty string
        f[0] = true;
        boolean dp[][] = new boolean[n + 1][n];
        // from top-left to bot-right
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (f[j] && dict.contains(s.substring(j, i))) {
                    f[i] = true;
                    dp[i][j] = true;
                }
            }
        }
        List<String> path = new ArrayList<String>();
        // scan from right to left
        gen_path(s, dp, s.length(), path, result);
        return result;
    }

    private void gen_path(String s, boolean[][] dp, int start, 
        List<String> path, List<String> result) {
        if (start == 0) {
            String tmp = new String();
            ListIterator li = path.listIterator(path.size());
            while (li.hasPrevious())
                tmp += li.previous() + " ";
            tmp = tmp.substring(0, tmp.length() - 1);
            result.add(tmp);
            return;
        }

        for (int i = start - 1; i >= 0; i--) {
            if (dp[start][i] == true) {
                path.add(s.substring(i, start));
                gen_path(s, dp, i, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
}