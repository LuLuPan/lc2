/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
containing all ones and return its area.

Solution: from top to bot, accumulate elements col by col
          for each row, take it as histgram and caluclate its max area
          by using incremental stack.

Notice: It is different for histogram to calculate area as bar in water container

*/
public class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        if (n == 0) return 0;
        int max_area = 0;
        // since it is not supposed to change matrix, so cannot use in place
        int[] num = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    num[j] = matrix[i][j] == '1' ? 1 : 0;
                } else {
                    num[j] = matrix[i][j] == '1' ? num[j] + 1 : 0;
                }
            }
            max_area = Math.max(max_area, largestRectangleArea(num));
        }

        return max_area;
    }

    private int largestRectangleArea(int[] row) {
        // incremental stack to store index
        Stack<Integer> stk = new Stack<Integer>();
        int n = row.length;
        int[] height = new int[n + 1];
        System.arraycopy(row, 0, height, 0, n);
        // add right most index
        height[n] = 0;
        int max_area = 0;
        // if n hist, there should n+1 bars to represent index.
        for (int i = 0; i <= n;) {
            if (stk.empty() || height[i] > height[stk.peek()])
                stk.push(i++);
            else {
                int idx = stk.peek();
                stk.pop();
                int width = stk.empty() ? i : i - stk.peek() - 1;
                //Notice: use height[stack.peek] as height
                // since after this one pop out of stack, current hist is 
                // lower than it, and element left in stack also lower than it.
                // cur is c, left in stack is a, b is just poped.
                // Except a and c, b is the lowest amoung range of (a, c)
                // since if there is a d within (a, c) and lower than b
                // there will no such (a, c) range
                max_area = Math.max(max_area, height[idx] * width);
            }
        }

        return max_area;
    }
}