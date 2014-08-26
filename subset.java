/*
Given a set of distinct integers, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

Solution: Sort array, 1. brute force?
          2. DFS: each element has two options, selected or not selected.
Complexity: Complexity: O(2^n) + O(n)
Corner case:

*/
public class Solution {
    public List<List<Integer>> subsets(int[] S) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (S.length == 0) return result;

        int n = S.length;
        Arrays.sort(S);

        List<Integer> path = new ArrayList<Integer>();
        dfs(S, 0, path, result);

        return result;
    }

    private void dfs(int[] S, int steps, List<Integer> path, List<List<Integer>> result)
    {
        // since no selection dfs, element at front still need steps==n to reach
        // terminate condition
        if (steps == S.length) {
            List<Integer> tmp = new ArrayList<Integer>(path);
            result.add(tmp);
            return;
        }

        // Not select, dfs to next directly
        dfs(S, steps + 1, path, result);
        //Select, enter path
        path.add(S[steps]);
        dfs(S, steps + 1, path, result);
        path.remove(path.size() - 1);
    }
}
