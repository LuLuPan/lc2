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
        if (digits == null) return result;
        List<String> kb = Arrays.asList(" ", "", "abc", "def", "ghi", "jkl",
                                        "mno", "pqrs", "tuv", "wxyz");
        helper(digits, kb, "", 0, result);
        return result;
    }
    
    private void helper(String digits, List<String> kb, String path, int cur, List<String> result) {
        if (cur == digits.length()) {
            result.add(new String(path));
            return;
        }
        
        int num = digits.charAt(cur) - '0';
        for (char c : kb.get(num).toCharArray()) {
            helper(digits, kb, path + c, cur + 1, result);
        }
    }
}