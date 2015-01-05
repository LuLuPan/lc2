/*
Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.
*/

/*
Zeros depend on how many 2, 5 pairs, however, much more 2 than 5 in n
so it depends on how many 5s.

For example 100

100 / 5 = 20
Number of multipliers of 5
5, 10, 15, 20, 25, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100

20 / 5 = 4
Number of multipliers of 5x5
25, 50, 75, 100

20 + 4 in total
*/

public class Solution {
    public int trailingZeroes(int n) {
        int result = 0;
        while (n > 0) {
            result += n / 5;
            n /= 5;
        }
        
        return result;
    }
}