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
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 != 0) return false;
        Stack<Character> stack = new Stack<Character>();

        int i = 0;

        while (i < n) {
        	char c = s.charAt(i++);
        	switch (c) {
        		case ')':
                    if (stack.empty() || '(' != stack.peek())
                    	return false;
                    stack.pop();
                break;
                case ']':
                    if (stack.empty() || '[' != stack.peek())
                    	return false;
                    stack.pop();
                break;
                case '}':
                    if (stack.empty() || '{' != stack.peek())
                    	return false;
                    stack.pop();
                break;
                default:
                    stack.push(c);
                break;
                
        	}
        }

        if (stack.size() > 0)
        	return false;
        return true;
    }
}