/*
Given a string, determine if it is a palindrome, considering only alphanumeric 
characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to 
ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
*/
public class Solution {
    public boolean isPalindrome(String s) {
        if (s.length() == 0) return true;

        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
        	if (!isAlpha(s.charAt(left))) left++;
        	else if (!isAlpha(s.charAt(right))) right--;
        	else if (Character.toLowerCase(s.charAt(left++)) != 
        		Character.toLowerCase(s.charAt(right--))) return false;
        }

        return true;
    }

    private boolean isAlpha(char c) {
    	// Palindrome could contain number
    	if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || 
    		(c >= '0' && c <= '9'))
    		return true;
    	return false;
    }
}