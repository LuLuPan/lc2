/*
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
*/
public class Solution {
    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0) return b;
        if (b == null || b.length() == 0) return a;

        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;
        while (i >= 0 || j >= 0) {
        	int val = (i < 0 ? 0 : (a.charAt(i) - '0')) + 
        	          (j < 0 ? 0 : (b.charAt(j) - '0')) + carry;
        	carry = val / 2;
        	val %= 2;
        	result.insert(0, val);
        	i--;
        	j--;
        }

        if (carry == 1)
        	result.insert(0, 1);
        return result.toString();
    }
}