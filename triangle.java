/*
Given a triangle, find the minimum path sum from top to bottom. 
Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, 
where n is the total number of rows in the triangle.

Solution: DP: From bot to top
          f(i, j) = min{f(i+1, j), f(i+1, j+1)} + triangle(i, j)
          i is level index, j is index in each row
          f(i, j) could use triangle in place
*/
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // from bot to top
        // use triangle in place
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++)
                triangle.get(i).set(j, Math.min(triangle.get(i + 1).get(j),
                    triangle.get(i + 1).get(j + 1)) + triangle.get(i).get(j));
        }
        return triangle.get(0).get(0);
    }
}