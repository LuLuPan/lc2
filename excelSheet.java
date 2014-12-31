/*
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 

26 number transfer to Oct.
*/

public class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int val = n % 26;
            if (val > 0)
                sb.append((char)(val - 1 + 'A'));
            else {
                // note: Z need to be considered seperately
                sb.append('Z');
                n--;
            }
            n /= 26;
        }
        
        return sb.reverse().toString();
    }
}