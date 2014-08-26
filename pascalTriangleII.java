/*
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?

Solution: Rolling array
*/
public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> array = new ArrayList<Integer>();
        // Notice: rowIndex start from 0
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = i - 1; j > 0; j--) {
                array.set(j, array.get(j) + array.get(j - 1));
            }
            array.add(1);
        }
        
        return array;
    }
    
}