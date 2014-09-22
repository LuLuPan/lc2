/*
Divide two integers without using multiplication, division and mod operator.
*/
public class Solution {
    public int divide(int dividend, int divisor) {
        int sign = (dividend > 0 ? 1 : -1) * (divisor > 0 ? 1 : -1);
        // Need long to avoid INT_MIN transfer to positive
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);

        int count = 0;

        while (a >= b) {
        	int multi = 1;
        	// if not enough for doubled, back to itself
        	long bb = b;
            // a could be small than bb * 2, but still could be bigger than b
        	while (a >= bb) {
        		a -= bb;
        		count += multi;
        		// Double to accelerate
        		if (bb < Integer.MAX_VALUE >> 1) {
        			bb += bb;
        			multi += multi;
        		}
        	}
        }

        return sign * count;
    }
}