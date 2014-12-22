// recursive
public class Solution {
    public double pow(double x, int n) {
        if (n < 0)
            return 1.0 / power(x, -n);
        return power(x, n);
    }
    
    private double power(double x, int n) {
        if (n == 0) return 1.0;
        double p = power(x, n / 2);
        if (n % 2 == 0)
            return p * p;
        else
            return p * p * x;
    }
}

// iterative
public class Solution {
    public double pow(double x, int n) {
        int sign = n > 0 ? 1 : -1;
        n = sign * n;
        double p = 1.0;
        while (n > 0) {
            // if n is even, it will become to 1 finally
            if (n % 2 == 1)
                p *= x;
            n /= 2;
            x *= x;
        }
        
        return sign == 1 ? p : 1.0 / p;
    }
}