/*
Given an unsorted array, find the maximum difference between the successive 
elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in 
the 32-bit signed integer range.
*/

/*
Solution: Radix Sort in O(n + k)
*/

public class Solution {
    public int maximumGap(int[] num) {
        if (num == null || num.length < 2)
            return 0;
        radixSort(num);
        int result = Math.abs(num[1] - num[0]);
        for (int i = 2; i < num.length; i++)
            if (Math.abs(num[i] - num[i - 1]) > result)
                result = Math.abs(num[i] - num[i - 1]);
        return result;
    }

    private int getMax(int[] num) {
        int max = num[0];
        for (int i : num)
            max = Math.max(max, i);
        return max;
    }

    private void radixSort(int[] num) {
        int max = getMax(num);
        for (int den = 1; max / den > 0; den *= 10)
            countSort(num, den);
    }

    private void countSort(int[] num, int den) {
        int n = num.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++)
            count[(num[i] / den) % 10]++;
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];
        for (int i = n - 1; i >= 0; i--) {
            output[count[(num[i] / den) % 10] - 1] = num[i];
            count[(num[i] / den) % 10]--;
        }

        for (int i = 0; i < n; i++)
            num[i] = output[i];
    }
}