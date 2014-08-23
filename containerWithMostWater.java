/*
Given n non-negative integers a1, a2, ..., an, where each represents a point 
at coordinate (i, ai). n vertical lines are drawn such that the two endpoints
of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis 
forms a container, such that the container contains the most water.

Note: You may not slant the container.

Solution: move towards middle from left and right,
          move left if left side is shorter than right, otherwise..
*/
public class Solution {
    public int maxArea(int[] height) {
        int n = height.length;
        if ( n < 2) return 0;
        int max_area = 0;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            max_area = Math.max(max_area, area);
            if (height[left] < height[right])
                left++;
            else right--;
        }

        return max_area;
    }
}