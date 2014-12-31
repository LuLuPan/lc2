/*
You are given a string, S, and a list of words, L, that are all of the same 
length. Find all starting indices of substring(s) in S that is a concatenation 
of each word in L exactly once and without any intervening characters.

For example, given:
S: "barfoothefoobarman"
L: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).

Solution: Similar to minimal substring windows
Maintain a word-count map to record word numbers showed up in substring
Complextiy: O(m*n)
Corner case: "a" and ["a"]
*/

public class Solution {
    public List<Integer> findSubstring(String S, String[] L) {
        List<Integer> result = new ArrayList<Integer>();
        if (S == null || L == null || L.length == 0) 
             return result;
        int m = S.length();
        int n = L.length;
        int len = L[0].length();
        
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (String str : L) {
            if (!map.containsKey(str))
                map.put(str, 0);
            map.put(str, map.get(str) + 1);
        }
        
        for (int i = 0; i <= m - n * len; i++) {
            HashMap<String, Integer> inMap = new HashMap<String, Integer>();
            int count = 0;
            for (int j = i; j < i + n * len; j += len) {
                String sub = S.substring(j,  j + len);
                if (map.containsKey(sub)) {
                    if (!inMap.containsKey(sub))
                        inMap.put(sub, 0);
                    inMap.put(sub, inMap.get(sub) + 1);
                    if (inMap.get(sub) <= map.get(sub))
                        count++;
                    else
                        break;
                } else break;
            }
            
            if (count == n)
                result.add(i);
        }
        
        return result;
    }
}