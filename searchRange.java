/*
Given a sorted array of integers, find the starting and ending position of 
a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

Solution: binary search, to find all target position and store into a list
Update better one: Binary search to find < target and < target + 1.

*/

public class Solution {
    public int[] searchRange(int[] A, int target) {
        int[] result = {-1, -1};
        int n = A.length;
        if (n == 0) return result;

        List<Integer> indices = new ArrayList<Integer>();
        findTarget(A, 0, n - 1, target, indices);
        // not found
        if (indices.size() == 0)
            return result;
        Collections.sort(indices);
        result[0] = indices.get(0);
        result[1] = indices.get(indices.size() - 1);
        return result;
    }

    private void findTarget(int[] A, int start, int end, int target, 
        List<Integer> indices)
    {
        // Error: consider start > end
        if (end < start)
            return;
        if (start == end) {
            if (A[start] == target)
                indices.add(start);
            return;
        }

        int middle = start + (end - start) / 2;
        if(A[middle] == target) {
            // if found, target still could occur in both side
            // keep looking for in both half
            indices.add(middle);
            findTarget(A, middle + 1, end, target, indices);
            findTarget(A, start, middle - 1, target, indices);
        }
        else if (A[middle] < target)
            findTarget(A, middle + 1, end, target, indices);
        else
            findTarget(A, start, middle - 1, target, indices);
    }
}

public class Solution {
    public int[] searchRange(int[] A, int target) {
        int[] result = {-1, -1};
        int n = A.length;
        if (n == 0) return result;

        int start = 0;
        int end = n - 1;
        // Error: not start <= end, or may infinite loop
        while (start < end) {
            int middle = start + (end - start) / 2;
            if (A[middle] < target)
                start = middle + 1;
            else
                end = middle; // not middle - 1
        }

        if (A[start] != target)
            return result;
        int low = start;
        start = 0;
        end = n - 1;
        while (start <= end) {
            int middle = start + (end - start) / 2;
            if (A[middle] < target + 1)
                start = middle + 1;
            else
                end = middle; // not middle - 1
        }

        int high = A[start] == target ? start : start - 1;
        result[0] = low;
        result[1] = high;

        return result;
    }
}