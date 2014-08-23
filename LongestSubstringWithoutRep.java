/*
Given a string, find the length of the longest substring without repeating 
characters. For example, the longest substring without repeating letters for 
"abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring
 is "b", with the length of 1.

 Solution: one traversal
 Notice: if duplication char has been found, previous char c and all chars
         before it should be discarded.
Complexity: O(n)
Corner case:
*/
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int result = 0;
        if (n == 0) return 0;

        int start = 0;
        // indicies all ASCII chars
        int[] indicies = new int[255];
        Arrays.fill(indicies, -1);
        for (int i = 0; i < n; i++) {
            int index = (int)s.charAt(i);
            // Error: should be >=
            //        should be >= start, since index will bigger than 0
            //        even there is no dup of this indexed char in cur substr
            if (indicies[index] >= start) {
                //dup found
                result = Math.max(result, i - start);
                start = indicies[index] + 1;
            }
            indicies[index] = i;
        }

        result = Math.max(result, n - start);
        return result;
    }
}