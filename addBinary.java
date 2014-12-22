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
        StringBuilder sb = new StringBuilder();
        int carrier = 0;
        int i = a.length() - 1, j = b.length() - 1;
        while (i >= 0 || j >= 0) {
            int sum = (i >= 0 ? a.charAt(i--) - '0' : 0) +
                      (j >= 0 ? b.charAt(j--) - '0' : 0) +
                      carrier;
            carrier = sum / 2;
            sb.append(sum % 2);
        }
        
        if (carrier != 0)
            sb.append(carrier);
        return sb.reverse().toString();
    }
}