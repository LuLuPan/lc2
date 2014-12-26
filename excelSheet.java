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
            int index = n % 26 - 1;
            n /= 26;
            char c = 'A';
            // note: Z need to be considered seperately
            if (index == -1) {
                c = 'Z';
                n--;
            }
            else
                c += (char)index;
                
            sb.append(c);
        }
        
        return sb.reverse().toString();
    }
}