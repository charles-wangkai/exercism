import java.util.ArrayList;
import java.util.List;

public class PrimeFactorsCalculator {
  List<Long> calculatePrimeFactorsOf(long n) {
    List<Long> result = new ArrayList<>();
    for (int i = 2; (long) i * i <= n; ++i) {
      while (n % i == 0) {
        result.add((long) i);
        n /= i;
      }
    }
    if (n != 1) {
      result.add(n);
    }

    return result;
  }
}
