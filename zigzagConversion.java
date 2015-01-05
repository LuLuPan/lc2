/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number 
of rows like this: (you may want to display this pattern in a fixed font for
 better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion 
given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/
public class Solution {
    public String convert(String s, int nRows) {
        if (nRows <= 1 || s.length() <= 1) return s;
        String result = new String();
        for (int i = 0; i < nRows; i++) {
            // index: index of element on vertical cols, for the first cols
            // index starts from i, one col elems + diag elems = nRows + nRows - 2
            // index will cover all elements that will fall in this line
            // so index < s.length()....
            for (int index = i; index < s.length(); index += (2 * nRows - 2)) {
                // vertical
                result += s.charAt(index);
                // diag
                if (i == 0 || i == nRows - 1) continue;
                // index of elements on diagnoal
                // index + (nRows - i - 1) * 2
                if (index + (nRows - i - 1) * 2 < s.length())
                    result += s.charAt(index + (nRows - i - 1) * 2);
            }
        }

        return result;
    }
}