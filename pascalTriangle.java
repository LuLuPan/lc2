/*
Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (numRows == 0) return result;

        List<Integer> line = new ArrayList<Integer>();
        line.add(1);
        result.add(new ArrayList<Integer>(line));
        
        for (int i = 1; i < numRows; i++) {
            line.clear();
            line.add(1);
            for (int j = 1; j < i; j++) {
                line.add(result.get(i - 1).get(j - 1) + 
                    result.get(i - 1).get(j));
            }
            line.add(1);
            result.add(new ArrayList<Integer>(line));
        }

        return result;
    }
}