/*
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
*/

/* Solution:
    0.16  
6 ) 1.00
    0 
    1 0       <-- Remainder=1, mark 1 as seen at position=0.
    - 6 
      40      <-- Remainder=4, mark 4 as seen at position=1.
    - 36 
       4      <-- Remainder=4 was seen before at position=1, so the fractional 
       part which is 16 starts repeating at position=1 => 1(6).
The key insight here is to notice that once the !!remainder!! starts repeating, 
so does the divided result.

You will need a hash table that maps from the remainder to its position of the 
fractional part. Once you found a repeating remainder, you may enclose the 
reoccurring fractional part with parentheses by consulting the position from the table.

The remainder could be zero while doing the division. That means there is no 
repeating fractional part and you should stop right away.

Just like the question Divide Two Integers, be wary of edge case such as 
negative fractions and nasty extreme case such as -2147483648 / -1.


*/
public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) return "";
        if (numerator == 0) return "0";
        StringBuilder result = new StringBuilder();
        if ((numerator < 0) ^ (denominator < 0))
            result.append("-");

        long a = Math.abs((long)numerator);
        long b = Math.abs((long)denominator);
        result.append(a / b);
        long rem = (a % b) * 10;

        if (rem == 0) return result.toString();
        result.append(".");

        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        while (rem != 0) {
            if (map.containsKey(rem)) {
                int start = map.get(rem);
                String sub1 = result.substring(0, start);
                String sub2 = result.substring(start, result.length());
                return sub1 + "(" + sub2 + ")";
            }

            map.put(rem, result.length());
            result.append(rem / b);
            rem = (rem % b) * 10;
        }

        return result.toString();
    }
}