/*
Given a [set] of candidate numbers (C) and a target number (T), find all unique 
combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. 
(ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 


Solution: sort and DFS
Complexity: O(n!) + O(n)
*/
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (candidates.length == 0) return result;
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<Integer>();
        dfs(candidates, 0, target, 0, path, result);
        return result;
    }

    private void dfs(int[] candidates, int start, int target, int sum, 
        List<Integer> path,
        List<List<Integer>> result)
    {
        if(sum == target) {
            result.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // since sorted, no need to try later element
            if (sum + candidates[i] > target)
                return;
            path.add(candidates[i]);
            // start should be i, or it will always unchanged
            dfs(candidates, start, target, sum + candidates[i], path, result);
            path.remove(path.size() - 1);
        }
    }
}