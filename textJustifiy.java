/*
Given an array of words and a length L, format the text such that each line has 
exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as 
you can in each line. Pad extra spaces ' ' when necessary so that each line has
exactly L characters.

Extra spaces between words should be distributed as evenly as possible. 
If the number of spaces on a line do not divide evenly between words, 
the empty slots on the left will be assigned more spaces than the slots
 on the right.

For the last line of text, it should be left justified and no extra space 
is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

click to show corner cases.

Corner Cases:
A line other than the last line might contain only one word. 
What should you do in this case?
In this case, that line should be left-justified.

Corner Case: [""], 0 should output [""]
*/
public class Solution {
    public List<String> fullJustify(String[] words, int L) {
        List<String> result = new ArrayList<String>("");
        if (L == 0 || words.length == 0) return result;

        int curLen = 0;   // current words length sum in current row
        int curStart = 0; // start word index in current row
        for (int i = 0; i < words.length; i++) {
            if (curLen + words[i].length() + i - curStart > L) {
                String line = formLine(L, curLen, curStart, i - curStart, words, false);
                result.add(new String(line));
                curStart = i;
                curLen = 0;
            }
            curLen += words[i].length();
        }
        // if words left for the last line but cannot fill up the line
        String line = formLine(L, curLen, curStart, words.length - curStart, words, true);
        result.add(new String(line));

        return result;
    }

    private String formLine(int L, int curLen, int curStart, int wordNum, 
        String[] words, boolean lastLine)
    {
        // when there is only one word, spaceNum will be zero
        // make it at lease one
        int spaceNum = wordNum - 1;
        spaceNum = spaceNum > 0 ? spaceNum : 1;
        int spaceLen = L - curLen;
        int count = 0;
        int[] spaces = new int[spaceNum];
        while (count < spaceLen) {
            spaces[count % spaceNum]++;
            count++;
            // for last line, words interval is only one space
            if (lastLine && count == spaceNum)
                break;
        }

        // form the line string
        StringBuilder line = new StringBuilder();
        for (int i = curStart; i < curStart + wordsNum; i++) {
            line.append(words[i]);
            
            if (i != curStart + wordsNum - 1) {
                for (int j = 0; j < spaces[i - curStart]; j++)
                    line.append(" ");
            }
        }
        

        while (line.length() < L)
            line += " ";

        return line;
    }
}