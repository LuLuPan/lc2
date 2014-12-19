/*
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.

Solution:
Only works because range is known.
Roman has additive and subtractive format.
Four characters are avoided being repeated in succession.
4 and 9 need to be included to simplify codes

So one pass of all radix numbers could generate the roman number
*/
public class Solution {
    // Note 1: consider 4xx and 9xx
    private final int[] radix = new int[] {1000, 900, 500, 400, 100,
            90, 50, 40, 10, 9, 5, 4, 1};

    private final String[] symbol = new String[] {"M", "CM", "D", "CD", "C",
            "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        // Note 2: num > 0
        for (int i = 0; num > 0 && i < radix.length; i++) {
            int count = num / radix[i];
            num %= radix[i];
            for (; count > 0; count--)
                result.append(symbol[i]);
        }

        return result.toString();
    }
}
