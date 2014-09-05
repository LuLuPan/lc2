/*
Given an array of strings, return all groups of strings that are anagrams.

Note: All inputs will be in lower-case.

Solution:
1. For two words, use two hashmap to match each character, <char, freq>
   O(n*(m+n))

2. For one word, sorted it and make it as key in HashMap and stringlist as value
   For second word, sorted it and find it in HashMap, if found, there is an
   anagram for it.

   Normal sort is o(nlogn), k is the max word length
   O(nklogk)

*/
public class Solution {
    public List<String> anagrams(String[] strs) {
    	List<String> result = new ArrayList<String>();
        if (strs.length <= 1) return result;

        HashMap<String, List<String>> strMap = new HashMap<String, List<String>>();
        for (int i = 0; i < strs.length; i++) {
        	char[] charArray = strs[i].toCharArray();
        	Arrays.sort(charArray);
        	String sortedStr = new String(charArray);
        	if (!strMap.containsKey(sortedStr)) {
        		List<String> anagram = new ArrayList<String>();
        		anagram.add(strs[i]);
        		strMap.put(sortedStr, anagram);
        	} else {
        		// find anagram
        		strMap.get(sortedStr).add(strs[i]);
        	}
        }

        Iterator iter = strMap.values().iterator();
        while (iter.hasNext()) {
        	List<String> anagram = (ArrayList<String>)iter.next();
        	// Error: should be > 1 other than > 0, at least two
        	if (anagram.size() > 1)
        		result.addAll(anagram);
        }

        return result;
    }
}