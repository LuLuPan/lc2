/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another 
expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

  Solution: Based on stack
            If num, push into stack
            If operator, pop two number if have
  */

 public class Solution {
    public int evalRPN(String[] tokens) {
        int n = tokens.length;
        if (n == 0) return 0;

        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        for (String str : tokens) {
        	if (!str.equals("+") && !str.equals("-") && !str.equals("*") &&
        		!str.equals("/")) {
        		stack.push(Integer.parseInt(str));
        	} else {
        		int a = 0, b = 0;
        		if (stack.size() >= 2) {
        		    a = stack.pop();
        		    b = stack.pop();
        		}

        		switch (str.charAt(0)) {
        			case '+':
        			    stack.push(a + b);
        			    break;
        			case '-':
        			    stack.push(b - a);
        			    break;
        			case '*':
        			    stack.push(a * b);
        			    break;
        			case '/':
        			    stack.push(b / a);
        			    break;
        		}
        	}
        }

        return stack.peek();
    }
}