/*
Given a string S, find the length of the longest substring T that contains at most two 
distinct characters.
For example,
Given S = “eceba”,
T is "ece" which its length is 3.
*/
class solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // i: start index of current substring window
        // j: index of the nearest character different with current character
        // s[j] == s[k - 1], s[j] != s[k].... next time when s[k - 1] != s[k]
        // s[j] should be equal to s[k], otherwise, the third character appears
        // a b a a b c
        // 
        int i = 0, j = -1, maxLen = 0;
        for (int k = 1; k < s.length(); k++) {
            if (s.charAt(k) == s.charAt(k - 1)) continue;
            if (j >= 0 && s.charAt(j) != s.charAt(k)) {
                maxLen = Math.max(k - i, maxLen);
                i = j + 1;
            }
            j = k - 1;
        }
        
        return Math.max(s.length() - i, maxLen);
    }
}