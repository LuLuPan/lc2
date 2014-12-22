/*
Given two numbers represented as strings, return multiplication of the 
numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.
*/
public class Solution {
    public String multiply(String num1, String num2) {
        // no need to consider sign since non-negative
        int m = num1.length();
        int n = num2.length();
        if (m == 0 || n == 0) return null;
        int[] result = new int[m + n];

        int carrier = 0;

        for (int i = 0; i < m; i++) {
        	int a = num1.charAt(m - 1 - i) - '0';
        	// Error: Need to clear carrier, since it is already added
        	carrier = 0;
        	for (int j = 0; j < n; j++) {
        		int b = num2.charAt(n - 1 - j) - '0';
        		result[i + j] += a * b + carrier;
        		carrier = result[i + j] / 10;
        		result[i + j] %= 10;
        	}
        	result[i + n] += carrier;
        }

        int i = m + n - 1;
        // remove extra prefix in the array
        while ( i >= 0 && result[i] == 0)
        	i--;
        // Notice: could be all zero
        if (i < 0)
        	return "0";
        StringBuilder sb = new StringBuilder();
        while (i >= 0)
        	sb.append((char)(result[i--] + '0'));
        return sb.toString();
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

