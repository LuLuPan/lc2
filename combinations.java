/*
Given two integers n and k, return all possible combinations of k 
numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]


Solution: DFS
Complexity: O(n!) + O(n)

*/
public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (n == 0 || k == 0 || k > n) return result;
        // create 1...n
        int num[] = new int[n];
        for (int i = 0; i < n; i++)
            num[i] = i + 1;

        List<Integer> path = new ArrayList<Integer>();

        dfs(num, k, 0, path, result);

        return result;
    }

    private void dfs(int[] num, int k, int start, List<Integer> path, 
        List<List<Integer>> result)
    {
        if (path.size() == k) {
            result.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = start; i < num.length; i++) {
            path.add(num[i]);
            // Error: should be i + 1 other than start + 1, or infinite loop
            dfs(num, k, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }
}