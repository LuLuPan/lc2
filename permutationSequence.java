/*
The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.


Solution: 1. Emunate until kth by DFS
             Will time out
          2. Cony code method
          n will have n! permutations, n * (n - 1)!
          which also means ai change each (n - i)! times
          Besides start digit, there will be (n - 1)! sub permutations
          so each start digite will have (n - 1)! combinations.
          k / !(n - 1) to determine the first digit
          After find the first digits, next is to find the start in rest
          sub permutations, to find the k % !(n - 1) th.
          So on...

*/
public class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder result = new StringBuilder();
        if (n <= 0) return result.toString();

        boolean[] visited = new boolean[n];
        int[] count = new int[1];

        dfs(n, k, 0, "", visited, result);

        return result.toString();
    }

    private void dfs(int n, int k, int steps, String path, boolean[] visited,
    	StringBuilder result)
    {
    	if (count[0] == k)
    		return;

    	if (steps == n) {
    		count[0]++;
    		if (count[0] == k) {
    			result.append(path);
    		}

    		return;
    	}

    	for (int i = 0; i < n; i++) {
    		if (visited[i] == true)
    			continue;
    		visited[i] = true;
    		dfs(n, k, steps + 1, path + i + 1, visited, result);
    		visited[i] = false;
    	}
    }
}

public class Solution {
    public String getPermutation(int n, int k) {
    	StringBuilder result = new StringBuilder();
        if (n <= 0) return result.toString();

        // n!
        int permCount = 1;
        List<Integer> num = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
        	num.add(i);
        	permCount *= i;
        }
        // index starts from 0
        k--;
        for (int i = 0; i < n; i++) {
        	permCount /= (n - i);
        	int index = k / permCount;
        	k %= permCount;
        	result.append(num.get(index));
            // Notice: remove used number index
        	num.remove(index);
        }

        return result.toString();
    }
}

