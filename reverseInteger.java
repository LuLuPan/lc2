/*
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321
*/
public class Solution {
    public int reverse(int x) {
        if (x == Integer.MIN_VALUE) return 0;
        int y = 0;
        int sign = x < 0 ? -1 : 1;
        x = Math.abs(x);
        while (x != 0) {
            if (y > Integer.MAX_VALUE / 10 || (y == Integer.MAX_VALUE / 10) && (x % 10 > Integer.MAX_VALUE % 10))
                return 0;
            y = y * 10 + x % 10;
            x /= 10;
        }

        return y * sign;
    }
}