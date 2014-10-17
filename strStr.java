/*
Implement strStr().

Returns a pointer to the first occurrence of needle in haystack, 
or null if needle is not part of haystack.

1. Brutal Force O(m*n)
2. Rolling Hash O(2*m + n) => O(m + n)
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




public class Solution {
    private int generateHashBase(int len) {
        int base = 29;
        int curBase = 1;
        for (int i = len - 1; i >= 0; i--) {
            curBase *= base;
        }
        return curBase / base;
    }

    private int generateHash(String s, int len) {
        // big enough prime for character a - z
        int base = 29;
        int curBase = 1;
        int hash = 0;
        for (int i = len -1; i >= 0; i--) {
            hash += (int)s.charAt(i) * curBase;
            curBase *= base;
        }

        return hash; 
    }

    public String strStr(String haystack, String needle) {
        if (haystack == null || needle == null) return null;
        if (needle.length() == 0) return haystack;
        if (haystack.length() < needle.length()) return null;

        int m = haystack.length();
        int n = needle.length();

        int needleHash = generateHash(needle, n);
        int hayHash = generateHash(haystack, n);

        if (needleHash == hayHash) return haystack;
        int base = 29;
        int hashBase = generateHashBase(n);
        for (int i = n; i < m; i++) {
            hayHash = (hayHash - haystack.charAt(i - n) * hashBase) * base +
                      (int)haystack.charAt(i);

            if (hayHash == needleHash)
                return haystack.substring(i - n + 1);
        }

        return null;
    }
}
