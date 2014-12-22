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
        if (digits == null || digits.length == 0)
            return null;
        int n = digits.length;
        int[] result = new int[n + 1];
        int carry = 0;
        digits[n - 1] += 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i + 1] = digits[i] + carry;
            carry = result[i + 1] / 10;
            result[i + 1] %= 10;
        }
        
        if (carry != 0) {
            result[0] = 1;
        } else {
            result = Arrays.copyOfRange(result, 1, result.length);
        }
        return result;
    }
}