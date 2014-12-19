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
    class Pair {
        public int first;
        public int second;
        public Pair(int a, int b) {
            this.first = a;
            this.second = b;
        }
    }
    
    public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) return result;
        int n = num.length;
        Arrays.sort(num);
        HashMap<Integer, List<Pair>> map = new HashMap<Integer, List<Pair>>();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = num[i] + num[j];
                if (!map.containsKey(sum)) {
                    map.put(sum, new ArrayList<Pair>());
                }
                map.get(sum).add(new Pair(i, j));
            }
        }
        HashSet<List<Integer>> set = new HashSet<List<Integer>>();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int diff = target - num[i] - num[j];
                if (map.containsKey(diff)) {
                    for (Pair p : map.get(diff)) {
                        if (i <= p.second) continue;
                        List<Integer> quad = new ArrayList<Integer>();
                        quad.add(num[p.first]);
                        quad.add(num[p.second]);
                        quad.add(num[i]);
                        quad.add(num[j]);
                        if (set.add(quad))
                            result.add(quad);
                    }
                }
            }
        }
        
        return result;
    }
}