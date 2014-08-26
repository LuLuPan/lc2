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
        if (S.length() == 0 || L.length == 0) return result;
        int m = L[0].length();
        int n = S.length();
        int cat_len = L.length * m;
        if ( n < m) return result;

        HashMap<String, Integer> word_map = new HashMap<String, Integer>();
        for (String str : L) {
            if (word_map.containsKey(str)) {
                word_map.put(str, word_map.get(str) + 1);
            } else {
                word_map.put(str, 1);
            }
        }
        //Error: should be <= S.length() - cat_len
        // "a" and ["a"]
        for (int i = 0; i <= S.length() - cat_len; i++) {
            HashMap<String, Integer> new_map = new HashMap<String, Integer>();
            int appears = 0;
            for (int j = i; j < i + cat_len; j += m) {
                String word = S.substring(j, j + m);
                if (word_map.containsKey(word)) {
                    if (new_map.containsKey(word)) {
                        new_map.put(word, new_map.get(word) + 1);
                        if (new_map.get(word) <= word_map.get(word))
                            appears++;
                    } else {
                        new_map.put(word, 1);
                        appears++;
                    }
                } else {
                    break;
                }
            }

            if (appears == L.length)
                result.add(i);
        }

        return result;
    }
}