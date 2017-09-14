import java.util.ArrayList;
import java.util.List;

public class PrimeFactorsCalculator {
	List<Long> calculatePrimeFactorsOf(long n) {
		List<Long> primeFactors = new ArrayList<Long>();
		for (long i = 2; i * i <= n; i++) {
			if (isPrime((int) i)) {
				while (n % i == 0) {
					primeFactors.add(i);
					n /= i;
				}
			}
		}
		if (n != 1) {
			primeFactors.add(n);
		}
		return primeFactors;
	}

	boolean isPrime(int n) {
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
