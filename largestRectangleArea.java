/*
Given n non-negative integers representing the histogram's bar height 
where the width of each bar is 1, find the area of largest rectangle
in the histogram.

Solution: Incremental stack
*/
public class Solution {
    public int largestRectangleArea(int[] height) {
        int n = height.length;
        if (n == 0) return 0;
        int[] new_height = new int[n + 1];
        System.arraycopy(height, 0, new_height, 0 , n);
        // Notice: it is necessary for the right most bar
        // to make last one pop of stack
        new_height[n] = 0;
        // incremental stack to store hist's index
        Stack<Integer> stack = new Stack<Integer>();
        int max_area = 0;
        for (int i = 0; i <= n;) {
            if (stack.empty() || new_height[i] > new_height[stack.peek()])
                stack.push(i++);
            else {
                // if a lower bar showes up, pop all higher bar in the stack
                // until it is higher than peek's height then push to stack
                // process previous higher bar
                // cur highest one which width is one.
                int index = stack.peek();
                stack.pop();

                int width = stack.empty() ? i : i - stack.peek() - 1;
                max_area = Math.max(max_area, new_height[index] * width);
            }
        }

        return max_area;
    }
}