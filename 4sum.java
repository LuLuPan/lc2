/*
Given an array S of n integers, are there elements a, b, c, and d in S such 
that a + b + c + d = target? Find all unique quadruplets in the array which 
gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. 
(ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)

*/
public class Solution {
	public class Pair {
		public int left;
		public int right;
		public Pair(int l, int r) {
			this.left = l;
			this.right = r;
		}
	}

    public List<List<Integer>> fourSum(int[] num, int target) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
        int n = num.length;
        if (n < 4) return result;
        Arrays.sort(num);
        HashMap<Integer, List<Pair>> twoSum = new HashMap<Integer, List<Pair>>();
        HashSet<List<Integer>> set = new HashSet<List<Integer>>();
        for (int i = 0; i < n - 1; i++) {
        	for (int j = i + 1; j < n; j++) {
        		int sum = num[i] + num[j];
        		if (twoSum.containsKey(sum))
        			twoSum.get(sum).add(new Pair(i, j));
        		else {
        			List<Pair> list = new ArrayList<Pair>();
        			list.add(new  Pair(i, j));
        			twoSum.put(sum, list);
        		}
        	}
        }

        for (int i = 0; i < n - 1; i++) {
        	for (int j = i + 1; j < n; j++) {
        		int delta = target - num[i] - num[j];
        		if (twoSum.containsKey(delta)) {
        			for (Pair pair : twoSum.get(delta)) {
        				if (i <= pair.right)
        					// suppose [i, j] is at right of pair
        					continue;
        				List<Integer> quand = new ArrayList<Integer>();
        				quand.add(num[pair.left]);
        				quand.add(num[pair.right]);
        				quand.add(num[i]);
        				quand.add(num[j]);
        				if (set.add(quand))
        					result.add(quand);
        			}
        		}
        	}
        }

        return result;
    }
}
