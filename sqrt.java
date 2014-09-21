/*
Implement int sqrt(int x).

Compute and return the square root of x.

Solution: binary divide
*/
public class Solution {
    public int sqrt(int x) {
        int left = 1;
        int right = x / 2;

        if (x < 2) return x;
        int result = 0;
        while (left <= right) {
        	int mid = left + (right - left) / 2;
        	// do not use mid * mid to avoid overflow
        	if (x / mid > mid) {
        		left = mid + 1;
        		result = mid;
        	} else if (x / mid < mid) {
        		right = mid - 1;
        	} else {
        		return mid;
        	}
        }

        return result;
    }
}