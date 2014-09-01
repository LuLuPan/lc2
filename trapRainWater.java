/*
Given n non-negative integers representing an elevation map where the width of 
each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

Solution: Similar to binary search, highest bar divided into two parts
          process each sub-region similarly
*/
public class Solution {
    public int trap(int[] A) {
        int n = A.length;
    	if (n < 3) return 0;
    	int water = 0;
    	int maxIndex = 0;
    	int highest = A[0];

    	for (int i = 1; i < n; i++) {
    		if (A[i] > highest) {
    			highest = A[i];
    			maxIndex = i;
    		}
    	}

    	// There is a bar higher than peak at right side
    	for (int i = 0, peak = 0; i < maxIndex; i++) {
    		if (A[i] > peak) peak = A[i];
    		else water += peek - A[i];
    	}
        // There is a bar higher than peak at left side
    	for (int i = n - 1, peak = 0; i > maxIndex; i--) {
    		if (A[i] > peak) peak = A[i];
    		else water += peek - A[i];
    	}

    	return water;
    }
}