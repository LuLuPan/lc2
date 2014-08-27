/*
Given a string containing just the characters '(' and ')', find the length of
the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", 
which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is
 "()()", which has length = 4.

 Solution: 1. stack based, for '(' always push its index into stack
              for ')', always try to pop a '(' from stack.
              If stack is empty and input ')' which cannot be matched,
              keep its index, and if other unmatched ')', keep the last index
              If stack has '(' which not poped, peek its index, since it may not
              be matched.
           2. Two direcion scan

 */
 public class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n < 2) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0;
        int maxLen = 0;
        // last ')' index should be -1 at first
        int last = -1;
        while (i < n) {
        	char c = s.charAt(i);
        	if (c == '(')
        		stack.push(i);
        	else {
        		// ')'
        		// lastest unmatched ')'
        		if (stack.empty())
        			last = i;
        		else {
        			stack.pop();
        			if (stack.empty())
        				maxLen = Math.max(maxLen, i - last);
        			else
        				maxLen = Math.max(maxLen, i - stack.peek());
        		}
        	}
        	i++;
        }

        return maxLen;
    }
}