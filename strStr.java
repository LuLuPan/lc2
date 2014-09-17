/*
Implement strStr().

Returns a pointer to the first occurrence of needle in haystack, 
or null if needle is not part of haystack.
*/
public class Solution {
    public String strStr(String haystack, String needle) {
        if (haystack == null || needle == null) return null;
        if (needle.length() == 0) return haystack;
        if (haystack.length() < needle.length()) return null;

        int index = -1;
        // need j <= haystack.length() - needle.length()
        // or hi may overflow haystack at the end
        // missipi and isipi
    	for (int j = 0; j <= haystack.length() - needle.length(); j++) {
    	    int hi = j;
    		for (int i = 0; i < needle.length(); i++) {
    			if (needle.charAt(i) == haystack.charAt(hi)) {
    				if (index == -1) index = hi;
    				hi++; // may overflow haystack
    			}
    			else {
    				index = -1;
    				break;
    			}
    		}

    		if (index >= 0)
    			break;
    	}

        if (index == -1) return null;

        return haystack.substring(index);   
    }
}