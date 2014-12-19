/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid 
but "(]" and "([)]" are not.

Corner case:
"))", "{()}", "{}()"
So need default for switch state
*/
public class Solution {
    // HashMap will be easy to extend to more pair types
    private final HashMap<Character, Character> map = 
        new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return false;
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (stack.isEmpty() || stack.peek() != map.get(c))
                    return false;
                else stack.pop();
            } else {
                stack.push(c);
            }
        }
        
        return stack.isEmpty();
    }
}