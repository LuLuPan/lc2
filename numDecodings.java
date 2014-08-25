/*
A message containing letters from A-Z is being encoded to numbers using the 
following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of 
ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2

Solution: DP
Count[i] = Count[i-1] if S[i-1] is a valid char
else
Count[i] = Count[i -2] if S[i-1] is invalid
(cur = 0)
or = Count[i-1] + Count[i-2] if S[i-1] and S[i-2] together is still a valid char
else
(prev = 0, cout[i-2] = 0)
*/
public class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0 || s.charAt(0) == '0') return 0;
        int prev = 0;
        int cur = 1;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) == '0')
                cur = 0;
            if (i < 2 || !(s.charAt(i - 2) == '1' ||
                s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6'))
                prev = 0;
            int tmp = cur;
            cur += prev;
            prev = tmp;
        }

        return cur;
    }
}