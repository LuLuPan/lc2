/*
Given n, how many structurally unique BST's (binary search trees) that store 
values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3


Solution:
For i as root, its left subtree node are from [1..i-1], and right subtree
nodes are from [i+1, n]

Num of trees equals to num of left subtree multiplies num of right subtree
f(i) presents num of trees with root from 1..i
f(0) = 1
f(1) = 1
f(2) = f(0)*f(1) + f(1)*f(0) = 2
f(3) = f(0)*f(2) + // root as 1, left has 0, right has 2
       f(1)*f(1) + // root as 2, left has 1, right has 1
       f(2)*f(0) + // root as 3, left has 2, right has 0
DP: f(i) = sum(f(k-1) * f(i - k))    1<=k<=i


*/
public class Solution {
    public int numTrees(int n) {
        if (n == 0 || n == 1) return 1;
        int[] f = new int[n + 1];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i <= n; i++)
        	for (int k = 1; k <= i; k++)
        		f[i] += f[k - 1] * f[i - k];

        return f[n];
    }
}