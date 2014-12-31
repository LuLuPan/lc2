/*
Implement next permutation, which rearranges numbers into the 
lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest 
possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its 
corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

Solution:
1. From right to left, Find the first element violates incremental, mark as p
2. From right, find the fist element larger than p, mark as c, swap c with p

Right to left increment regions on right p, means this subregion is in the
greatest sub permutation.

p swap with the first element in the subregion greater than it, after swap,
subregion still in the incremental order. But new permutation is bigger
than original one

3. Reverse all elements on the right of p
After reverse, incremental subregion becomes decremental, become
smallest combination for elements in the subregion.
So the smallest one bigger than origin permutation

*/
public class Solution {
    public void nextPermutation(int[] num) {
        if (num == null || num.length < 2) return;
        int ri = -1;
        int n = num.length;
        for (int i = n - 1; i >= 1; i--) {
            if (num[i] > num[i - 1]) {
                ri = i - 1;
                break;
            }
        }
        
        if (ri != -1) {
            for (int i = n - 1; i > ri; i--) {
                if (num[i] > num[ri]) {
                    swap(i, ri, num);
                    break;
                }
            }
        }
        
        int start = ri + 1, end = n - 1;
        while (start < end) {
            swap(start, end, num);
            start++;
            end--;
        }
    }
    
    private void swap(int i, int j, int[] num) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }
}