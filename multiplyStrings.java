/*
Given two numbers represented as strings, return multiplication of the 
numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.
*/
// bit combination
/*
O((m + n) ^ 2) + O(1) Method based on multiply bit combination
             1 2 3
             4 5 6
        ------------
             7 3 8
          6 1 5
       4 9 2
  -----------------
       5 6 0 8 8 
1 bit: 1 bit from num1 x 1 bit from num2
        (3 x 6 + 0) % 10 = 8 (1)
10bit: 1bit from num1 x 10bit from num2 +
         10bit from num1 x 1bit from num2

        (3 x 5 + 2 x 6 + 1) % 10 = 28 % 10 = 8 (2)
100bit: 1bit from num1 x 100bit from num2 +
           10bit from num1 x 10bit from num2 +
           100bit from num1 x 1bit from num2

        (3 x 4 + 2 x 5 + 1 x 6 + 2) % 10 = 30 % 10 = 0 (3)
1000bit: 10bit from num1 x 100 bit from num2 +
             100bit from num1 x 10 bit from num2
        (2 x 4 + 1 x 5 + 3) % 10 = 16 % 10 = 6 (1)
10000bit: 100 bit from num1 x 100bit from num2
        (1 x 4 + 1) % 10 = 5
*/
public class Solution {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) return "";
        if (num1.length() == 0 || num2.length() == 0) return "0";
        if (num1.charAt(0) == '0' || num2.charAt(0) == '0')
            return "0";
            
        int m = num1.length();
        int n = num2.length();
        
        StringBuilder result = new StringBuilder();
        int num = 0;
        // i: bit index for result
        // j: bit index for num1
        for (int i = m + n; i > 0; i--) {
            // limit num1 index to first most bits if not enough num2 bits
            for (int j = Math.min(m, i - 1); j > 0; j--) {
                // gurantee num2 has enough bit
                if (i - j <= n) {
                    int a = (int)(num1.charAt(j - 1) - '0'); // num1
                    int b = (int)(num2.charAt(i - j - 1) - '0'); // num2
                    num += a * b;
                }
            }
            
            // if i == 1, num > 0, only carry bit left
            if (i != 1 || num > 0)
                result.append(num % 10);
            num /= 10; // carry bit
        }
            
            
        return result.reverse().toString();
    }
}

public class Solution {
    public String multiply(String num1, String num2) {
        // no need to consider sign since non-negative
        int m = num1.length();
        int n = num2.length();
        if (m == 0 || n == 0) return null;
        int[] result = new int[m + n];
        
        // store output from right to left
        // LSB to MSB
        // make index more concise
        for (int i = m - 1; i >= 0; i--) {
            int carrie = 0;
            int a = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int b = num2.charAt(j) - '0';
                int val = a * b + carrie;
                // Note: Should be += other than =, otherwise may overwrite
                // intermediate result
                result[i + 1 + j] += val;
                carrie = result[i + 1 + j] / 10;
                result[i + 1 + j] %= 10;
            }
            
            result[i] += carrie;
        }
        
        int i = 0;
        while ((i < m + n) && result[i] == 0)
            i++;
        if (i == m + n) return "0";
        StringBuilder sb = new StringBuilder();
        while (i < m + n) {
            sb.append(result[i++]);
        }
        
        return sb.toString();
    }
}

