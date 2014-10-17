/*
You have k lists of sorted integers. Find the smallest range that includes at
 least one number from each of the k lists. 

For example, 
List 1: [4, 10, 15, 24, 26] 
List 2: [0, 9, 12, 20] 
List 3: [5, 18, 22, 30] 

The smallest range here would be [20, 24] as it contains 24 from list 1, 20 
from list 2, and 22 from list 3.
*/
class Solution {
    // Solution: Create a structure with value and list index
    // merge k list, find the min window which contains nodes
    // from all lists

	public class Node {
		int val;
		int listIndex;
		public Node(int v, int idx) {
			val = v;
			listIndex = idx;
		}
	}

	public List<Integer> findRange(List<List<Integer>> lists) {
        List<Integer> result = new ArrayList<Integer>(2);
        result.add(-1);
        result.add(-1);
        if (lists == null || lists.size() < 2) return result;

        int appears = 0;
        int minWin = Integer.MAX_VALUE;
        int k = lists.size();
        int[] map = new int[k];
        ListIterator<List<Integer>> iter = lists.listIterator();
        List<Node> merged = new ArrayList<Node>();
        initMerged(merged, iter.next());
        int index = 1;
        while (iter.hasNext()) {
            mergeTwo(merged, iter.next(), index++);
        }
        
        int start = 0;
        for (int i = 0; i < merged.size(); i++) {
            int listindex = merged.get(i).listIndex;
            if (map[listindex] == 0)
                appears++;
            map[listindex]++;
            
            if (appears == k) {
                while (map[merged.get(start).listIndex] > 1) {
                    map[merged.get(start++).listIndex]--;
                }
                
                if (minWin > merged.get(i).val - merged.get(start).val) {
                    minWin = merged.get(i).val - merged.get(start).val;
                    result.set(0, merged.get(start).val);
                    result.set(1, merged.get(i).val);
                }
            }
        }
        
        return result;
    }

    private void initMerged(List<Node> merged, List<Integer> l) {
        ListIterator<Integer> iter = l.listIterator();
        while (iter.hasNext()) {
            merged.add(new Node(iter.next(), 0));
        }
    }

    private void mergeTwo(List<Node> merged, List<Integer> l, int index)
    {
        ListIterator<Integer> iter1 = l.listIterator();
        int i = 0;
        for (i = 0; i < merged.size() && iter1.hasNext();) {
            int a = merged.get(i).val;
            int b = iter1.next();
            if (a > b) {
                merged.add(i++, new Node(b, index));
            } else {
                i++;
                iter1.previous();
            }
        }

        while (iter1.hasNext()) {
            merged.add(new Node(iter1.next(), index));
        }
    }
}