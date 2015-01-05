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
            return new int[0];
        int carrier = 0;
        int n = digits.length;
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int sum = (i == n - 1 ? 1 : 0) + digits[i] + carrier;
            carrier = sum / 10;
            res[i] = sum % 10;
        }
        
        if (carrier != 0) {
            int[] result = new int[n + 1];
            System.arraycopy(digits, 0, result, 1, n);
            result[0] = 1;
            return result;
        }
        return res; 
    }
}

public class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        int[] result = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] == 9)
                result[i] = 0;
            else {
                // no more carry bit, return result after copy rest array
                result[i] = digits[i] + 1;
                System.arraycopy(digits, 0, result, 0, i);
                return result;
            }
        }
        
        result = new int[n + 1];
        result[0] = 1;
        return result;
    }
}

public class Solution {
    public void plusOne(List<Integer> digits) {
        if (digits == null || digits.size() == 0)
            return;
        for (int i = digits.size() - 1; i >= 0; i--) {
            if (digits.get(i) < 9) {
                digits.set(i, digits.get(i) + 1);
                return;
            } else {
                digits.set(i, 0);
            }
        }
        // add one more bit if all digits are 9
        digits.add(0);
        digits.set(0, 1);
    }
}