public class Solution {
    public int maxProduct(int[] A) {
        if (A.length == 0) return 0;
        int n = A.length;
        
        int result = Integer.MIN_VALUE;
        int curMax = 1;
        int curMin = 1;
        int preMax = 1;
        for (int i = 0; i < n; i++) {
            preMax = Math.max(curMax, 1);
            if (A[i] > 0) {
                curMax = preMax * A[i]; 
                curMin = curMin * A[i];
            } else {
                curMax = curMin * A[i];
                curMin = preMax * A[i];
            }
            
            result = Math.max(result, curMax);
        }
        
        return result;
    }
}