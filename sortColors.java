/*
Given an array with n objects colored red, white or blue, sort them so that
objects of the same color are adjacent, with the colors in the order red, 
white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, 
white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.
*/

/*
Solution: 1. two index for red and blue. and scan from left and right towards
             middle, swap element
          2. partition based on quick sort
Corner case:
*/

public class Solution {
    public void sortColors(int[] A) {
        if (A.length == 0 || A.length == 1) return;
        int red = 0;
        int blue = A.length - 1;
        // Error: i <= blue
        for (int i = 0; i <= blue;) {
            if (A[i] == 0) {
                //swap red to front
                A[i++] = A[red];
                A[red++] = 0;
            } else if (A[i] == 2) {
                //swap blue to back
                // Notice: do not move i here
                A[i] = A[blue];
                A[blue--] = 2;
            } else {
                i++;
            }
        }
    }
}