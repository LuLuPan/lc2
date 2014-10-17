public class Solution {
    /*
     * a1<a2>a3<a4>a5<a6
     * Solution:
     * 1) Sort as 1, 2, 3, 4, 5, 6
     * 2) rolling even indexed element to replace previous one
     *    1, 4, 3, 6, 5, 2
     *    For even length, swap last two
     *    1, 4, 3, 6, 2, 5
     */
    public void waveOrder(int[] num) {
    	if (num == null || num.length < 2)
    		return;
    	
    	// if in place, need to implement a in-place sort
    	Arrays.sort(num);
    	int oddSmallest = num[1];
    	
    	int n = num.length;
    	for (int i = 3; i < n; i += 2) {
    		num[i - 2] = num[i];
    	}
    	
    	if (n % 2 == 1) {
    	    num[n - 2] = num[n - 1];
    	}
    	num[n - 1] = oddSmallest;
    	
    	if (n % 2 == 0) {
    		int tmp = num[n - 1];
    		num[n - 1] = num[n - 2];
    		num[n - 2] = tmp;
    	}
    }
}