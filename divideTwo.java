/*
Divide two integers without using multiplication, division and mod operator.
*/
public class Solution {
    public int divide(int dividend, int divisor) {
        //int sign = (dividend > 0 ? 1 : -1) * (divisor > 0 ? 1 : -1);
        // note 1
        int sign = 1;
        if ((dividend < 0) ^ (divisor < 0))
            sign = -1;

        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);

        // note 2
        long count = 0;
        while (a >= b) {
            int multi = 1;
            long bb = b;

            while (a >= bb) {
                a -= bb;
                count += multi;
                // note 3
                if (bb < Integer.MAX_VALUE >> 1) {
                    bb += bb;
                    multi += multi;
                }
            }
        }

        // note 4
        long result = sign * count;
        if (result > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        return sign * (int)count;
    }
}