/*
Given a string s consists of upper/lower-case alphabets and empty space 
characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space 
characters only.

For example, 
Given s = "Hello World",
return 5.
*/
public class Solution {
    public int lengthOfLastWord(String s) {
        if (s.length() == 0) return 0;

        int result = 0;
        //Corner case: "a  "
        for (int i = 0; i < s.length();) {
        	if (s.charAt(i++) != ' ')
        		result++;
        	else if (i < s.length() && s.charAt(i) != ' ')
        		result = 0;
        }

        return result;
    }
}