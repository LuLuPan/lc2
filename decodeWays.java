/*
Solution: DP
count[i]: how many ways to decode of previous i characters, and corresponding index is i - 1
Count[i] = Count[i-1] if S[i-1] is a valid char                                      ->'1 - 9'
else
Count[i] = Count[i -2] if S[i-1] is invalid                                          ->'0'
(cur = 0)
or = Count[i-1] + Count[i-2] if S[i-1] and S[i-2] together is still a valid char     ->'1x || 21 - 26'
else                                                                                 ->'00'
(prev = 0, cout[i-2] = 0)
*/
public class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int prev = 0; // count[i - 1]
        int cur = 1; // count[i]
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) == '0')
                cur = 0; //count[i - 1]
            if (i < 2 || !(s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6')))
                prev = 0; // count[i - 2]
            int tmp = cur;
            cur += prev;
            prev = tmp;
        }
        
        return cur;
    }
}