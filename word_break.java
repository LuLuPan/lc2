/*
Given a string s and a dictionary of words dict, determine if s can be 
segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".

Solution: DP
f[i] = Anyof(f[j] && s[j+1, i] belongs to dict), 0<=j<i<n
*/
public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        int n = s.length();
        // +1 for empty
        boolean[] f = new boolean[n + 1];
        f[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (f[j] == true && dict.contains(s.substring(j, i))) {
                    f[i] = true;
                    // find a matched one, move forward
                    break;
                }
            }
        }

        return f[n];
    }
}