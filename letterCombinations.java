/*
Given a digit string, return all possible letter combinations that
the number could represent.

A mapping of digit to letters (just like on the telephone buttons)
is given below.

Solution: DFS
Notice: since digits determine the search sequence, no loop neeeded in dfs
Complexity: O(3^n) + O(n)
Corner case: input "" should return [""] other than []
*/

public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        List<String> kb = Arrays.asList(" ", "", "abc", "def", "ghi",
                            "jkl", "mno", "pqrs", "tuv", "wxyz");
        //Error: path is initated as "" in case of digits=""
        String path = new String("");

        dfs(kb, digits, path, 0, result);
        return result; 
    }

    private void dfs(List<String> kb, String digits, String path,
        int cur, List<String> result)
    {
        if (path.length() == digits.length()) {
            result.add(new String(path));
            return;
        }

        int num = digits.charAt(cur) - '0';
        for (int i = 0; i < kb.get(num).length(); i++) {
            dfs(kb, digits, path + kb.get(num).charAt(i), cur + 1, result);
        }
    }
}
