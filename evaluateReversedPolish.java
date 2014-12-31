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

// Extensible
public class Solution {
    interface Operator {
        int eval(int x, int y);
    }
    
    private final HashMap<String, Operator> OPERATORS = new HashMap<String, Operator>() {{
        put("+", new Operator() {
            public int eval(int x, int y) { return x + y;}
        });
        
        put("-", new Operator() {
            public int eval(int x, int y) { return x - y;}
        });
        
        put("*", new Operator() {
            public int eval(int x, int y) { return x * y;}
        });
        
        put("/", new Operator() {
            public int eval(int x, int y) { return x / y;}
        });
    }};
    
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0)
            return 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (String str : tokens) {
            if (!OPERATORS.containsKey(str)) {
                stack.push(Integer.parseInt(str));
            } else {
                int y = stack.pop();
                int x = stack.pop();
                // Error: Cannot use eval(stack.pop(), stack.pop())
                // For input "0", "3"
                // Since in parameters, firsst parameter will be 
                // used at first, so x = 3, y = 0, then error 3 / 0
                stack.push(OPERATORS.get(str).eval(x, y));
            }
        }
        
        return stack.pop();
    }
}