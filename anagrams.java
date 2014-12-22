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
        if (strs == null || strs.length < 2)
            return result;
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String newStr = new String(arr);
            if (!map.containsKey(newStr)) {
                map.put(newStr, new ArrayList<String>());
            }
            map.get(newStr).add(str);
        }
        
        Iterator<List<String>> it = map.values().iterator();
        while (it.hasNext()) {
            List<String> list = (ArrayList<String>)it.next();
            // Error: should be > 1 other than > 0, at least two
            if (list.size() > 1)
                result.addAll(list);
        }
        
        return result;
    }
}