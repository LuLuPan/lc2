/*
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

click to show clarification.

Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.
*/
public class Solution {
    // one pass
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return "";
        StringBuilder reversed = new StringBuilder();
        // Notice: j = n, " 1"
        int j = s.length();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ')
                j = i;
            else if (i == 0 || s.charAt(i - 1) == ' ') {
                if (reversed.length() != 0)
                    reversed.append(' ');
                reversed.append(s.substring(i, j));
            }
        }
        
        return reversed.toString();
    }
}