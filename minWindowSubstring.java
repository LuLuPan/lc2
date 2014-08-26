/*
Given a string S and a string T, find the minimum window in S which will 
contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the 
emtpy string "".

If there are multiple such windows, you are guaranteed that there will always
 be only one unique minimum window in S.


 Solution: Find a region contains all target chars by using two pointer
 First pointer unchange, and second move forward to find first region.
 And then shrink it by moving first pointer and second pointer at the same
 time until second reach end
 */
public class Solution {
    public String minWindow(String S, String T) {
        String result = new String();
        if (S.length() == 0 || S.length() < T.length()) return result;

         // ASCII map for T
        int[] expected = new int[256];
        // map for S
        int[] appeared = new int[256];
        for (int i = 0; i < T.length(); i++) {
            expected[T.charAt(i)]++;
        }

        int appears = 0;
        int minWindow  = Integer.MAX_VALUE;
        int winStart = 0;
        int start = 0;
        for (int i = 0; i < S.length(); i++) {
            if (expected[S.charAt(i)] > 0) {
                // find one matched
                appeared[S.charAt(i)]++;
                if (appeared[S.charAt(i)] <= expected[S.charAt(i)])
                    appears++;
            } 

            if (appears == T.length()) {
                // all matched and move start point to squeenze window
                while (expected[S.charAt(winStart)] == 0 ||
                    appeared[S.charAt(winStart)] > expected[S.charAt(winStart)]) {
                    //winStart++;
                    // Error:  cannot change winStart before access it indexed
                    appeared[S.charAt(winStart++)]--;
                }

                if (minWindow > i - winStart + 1) {
                    minWindow = i - windStart + 1;
                    start = winStart;
                }
            }
        }

        if (minWindow == Integer.MAX_VALUE)
            return result;
        result = S.substring(start, start + minWindow);
        return result;
    }
}
