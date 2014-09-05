/*
Write a function to find the longest common prefix string amongst an 
array of strings.
*/
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder result = new StringBuilder();
        if (strs == null || strs.length == 0) return result.toString();
        for (int i = 0; i < strs[0].length(); i++) {
        	for (int j = 1; j < strs.length; j++) {
        		if ((i >= strs[j].length()) || (strs[0].charAt(i) != strs[j].charAt(i)))
        	    {
        			return result.toString();
        		}
        	}

        	result.append(strs[0].charAt(i));
        }

        return result.toString();
    }
}