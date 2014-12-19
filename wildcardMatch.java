/*
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
*/
public class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        
        int star = -1;
        int mark = -1;
        int i = 0;
        int j = 0;
        while (i < s.length()) {
            if (j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                // matched
                i++;
                j++;
            } else if (j < p.length() && p.charAt(j) == '*') {
                // record * position, move one step of pattern and try to match
                // for next character
                // at first this * consume no character in text
                star = j++;
                mark = i;
            } else if (star != -1) {
                // if not matched, go back and consume one character of text
                // and use the character follow the * to try to match
                j = star + 1;
                i = ++mark;
            } else {
                return false;
            }
        }
        
        while (j < p.length())
            if (p.charAt(j++) != '*')
                return false;
        return true;
        
        // if pattern has not been consumed, what left should be all *
        //while (j < p.length() && p.charAt(j) == '*')
        //    j++;
        
        //return j == p.length();
    }
}