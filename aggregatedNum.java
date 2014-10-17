/* Aggregated Number
 * 112358, because 1+1=2, 1+2=3, 2+3=5, 3+5=8 
   122436, because 12+24=36 
   1299111210, because 12+99=111, 99+111=210 
   112112224, because 112+112=224 
 * DP:
 * dp[i] == true means from i to the end, there is a aggregated number
 * for dp[i-1], start from len=3, to check if it is a aggregated number
 * without considering overlap case.
 * if a range exclude end is a aggregated, then check if there is another
 * aggregated, if has, then whole range is aggregated.
 */

public class AggregatedNumber {
	public boolean isAggregatedNum(String num) {
		if (num == null || num.length() < 3) return false;
		int len = num.length();
		
		boolean[] dp = new boolean[len];
		
		dp[len - 1] = false;
		dp[len - 2] = false;
		dp[len - 3] = checkAggregate(num.substring(len - 3, len));
		
		for (int i = len - 4; i >= 0; i--) {
			boolean checked = false;
			for ( int j = i + 3; j <= len; j++) {
				dp[i] = checkAggregate(num.substring(i, j));
				if (dp[i] == true) {
					if (j < len) {
					for (int k = i + 1; k < j; k++)
						if (dp[k] == true) {
							checked = true;
							break;
						}
					} else {
						checked = true;
					}
				}
				
				if (checked == true)
					break;
			}
			
			dp[i] = checked;
		}
			
		return dp[0];
	}
		
	private boolean checkAggregate(String num) {
		int n = num.length();
		for (int i = 0; i < n - 2; i++) {
			for (int j = i + 1; j < n - 1; j++) {
				
				try {
					int a = Integer.parseInt(num.substring(0, i + 1));
					int b = Integer.parseInt(num.substring(i + 1, j + 1));
					int c = Integer.parseInt(num.substring(j + 1, n));
					if (a + b == c)
					    return true;
				} catch (NumberFormatException e) {
					//ignore
				}
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		AggregatedNumber an = new AggregatedNumber();
		String test = new String("112358");
		boolean result = an.isAggregatedNum(test);
		System.out.println(result);
	}
}
