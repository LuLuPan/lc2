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


public static double sqrt(double number) {
    if (number < 0)
            return Double.NaN;
    double low = 0;
    double high = number > 1 ? number : 1;
    double eps = 0.000000001;
    int loops = 1000;
    double sqrt = 0;
    while (loops-- > 0) {
            sqrt = (low + high) / 2;
            if (Math.abs(sqrt * sqrt - number) <= eps)
                    break;
            else if (sqrt * sqrt > number)
                    high = sqrt;
            else
                    low = sqrt;
    }
    return sqrt;
}