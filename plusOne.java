/*
Given a non-negative number represented as an array of digits, 
plus one to the number.

The digits are stored such that the most significant digit is 
at the head of the list.

Solution: n + 1 space only needed when carry is 1 at the end, which will
          only happen when array is 9, 99, 999, etc.
          If input param could be changed, in place could used. Return directly
          when carry become 0 during loop
          If cannot change input, extra array is needed and need one extra copy.
*/
public class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        int[] result = new int[n + 1];
        if (n == 0) return result;
        // Error: Should be 1 since plus one
        int carrier = 1;
        for (int i = n; i >= 0; i--) {
        	int val = (i > 0 ? digits[i - 1] : 0) + carrier;
        	carrier = val / 10;
        	val %= 10;
        	result[i] = val;
        }

        // only n space needed
        if (result[0] == 0)
        	return Arrays.copyOfRange(result, 1, result.length);
        return result;
    }
}