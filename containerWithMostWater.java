/*
Given n non-negative integers a1, a2, ..., an, where each represents a point 
at coordinate (i, ai). n vertical lines are drawn such that the two endpoints
of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis 
forms a container, such that the container contains the most water.

Note: You may not slant the container.

Solution: move towards middle from left and right,
          move left if left side is shorter than right, otherwise..

          Area determine by the shortest line
          i from left to right, j from right to left
          For a range i to j, if height[i] < height[j], when use i as one line,
          move j to left cannot increase area, since 1) distance decreased, 
          even there is a higher height[j], still determined by line i.
          so only move i to right, could possibly increase area 

Time complexity: O(n)
*/
public class Solution {
    public int maxArea(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);
            if (height[left] < height[right])
                left++;
            else right--;
        }
        
        return maxArea;
    }
}