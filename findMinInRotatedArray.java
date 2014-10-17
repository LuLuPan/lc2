public class Solution {
    public int findMin(int[] A) {
        if (A == null || A.length == 0) return 0;
        int start = 0;
        int end = A.length - 1;
        int mid = 0;
        while (start <= end) {
            if (A[start] <= A[end]) break;
            mid = start + (end - start) / 2;
            if (A[mid] > A[start]) {
                // rotate point at right
                if (A[mid] > A[end] && A[start] > A[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            } else {
                // rotate point at left
                if (A[mid] < A[start] && A[start] > A[end])
                    end = mid;
                else
                    start = mid + 1;
            }
        }

        return A[start];
    }
}