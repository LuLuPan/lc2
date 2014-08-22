/*
Given a collection of integers that might contain duplicates, 
S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

Solution: DFS. Notice how to avoid duplication
Complexity: O(2^n) + O(n)
*/
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int n = num.length;
        if (n == 0) return result;
        Arrays.sort(num);
        List<Integer> path = new ArrayList<Integer>();
        dfs(num, 0, path, result);
        return result;
    }

    private void dfs(int[] num, int start, List<Integer> path, 
        List<List<Integer>> result)
    {
        // no terminate condition, since each path could be added to result
        result.add(new ArrayList<Integer>(path));

        for (int i = start; i < num.length; i++) {
            // avoid duplication
            // since same element add into path in the first loop when
            // start start from i 
            if (i != start && num[i] == num[i - 1]) continue;
            path.add(num[i]);
            dfs(num, i + 1, path, result);
            path.remove(path.size() - 1);
        }        
    }
}