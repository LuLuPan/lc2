/*
Given n pairs of parentheses, write a function to generate all combinations
 of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"

Solution: DFS, left should be always >= right
Complexity:
Corner case:

*/
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        if (n == 0) return result;

        dfs(n, 0, 0, "", result);
        return result;
    }

    private void dfs(int n, int l, int r, String path, List<String> result) {
        if (l == n && r == n) {
            result.add(new String(path));
            return;
        }

        if (l < n)
            dfs(n, l + 1, r, path + "(", result);
        if (l > r)
            dfs(n, l, r + 1, path + ")", result);
    }
}