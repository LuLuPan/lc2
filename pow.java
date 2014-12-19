public class Solution {

    public double pow(double x, int n) {

        int sign = n > 0 ? 1 : -1;

        n = sign * n;

        double p = 1.0;

        while (n > 0) {

            if (n % 2 != 0) {

                p *= x;

            }

            

            n /= 2;

            x *= x;

        }

        

        return (sign == -1 ? 1.0 / p : p);

    }

}
