package fox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Primes {

    public static List<Integer> getPrimes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        List<Integer> primes = new ArrayList<>();
        Arrays.fill(isPrime, true);
        for (int d = 2; d < n + 1; d++) {
            if (isPrime[d]) {
                primes.add(d);
                for (int i = (int) Math.pow(d, 2); i < n + 1; i += d) {
                    isPrime[i] = false;
                }
            }
        }
        return primes;
    }
}
