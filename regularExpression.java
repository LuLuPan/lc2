/*
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
*/
// Recursion
// cases:
// True:  ab, .*
//        aab, c*a*b
// False: aab, aac*aa*b

public class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        return regexMatch(s, 0, p, 0);
    }
    
    private boolean regexMatch(String s, int i, String p, int j) {
        if (j == p.length()) return s.length() == i;
        // if current p is not a 'x*' combination
        if (j == p.length() - 1 || p.charAt(j + 1) != '*') {
            if (i == s.length() || (p.charAt(j) != s.charAt(i) && p.charAt(j) != '.'))
                return false;
            else // matched one char, check next
                return regexMatch(s, i + 1, p, j + 1);
        }
        
        // try * to consume 0, or 1, or 2, ... characters in s
        while (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.')) {
            if (regexMatch(s, i, p, j + 2))
                return true;
            i++;
        }
        
        // if x in 'x*' cannot match with s(i), check next after *
        return regexMatch(s, i, p, j + 2);
    }
}

// DP