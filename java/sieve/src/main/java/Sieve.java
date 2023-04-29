import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Sieve {
  int maxPrime;

  Sieve(int maxPrime) {
    this.maxPrime = maxPrime;
  }

  List<Integer> getPrimes() {
    boolean[] primes = new boolean[maxPrime + 1];
    Arrays.fill(primes, true);

    for (int i = 2; i < primes.length; ++i) {
      for (long j = (long) i * i; j < primes.length; j += i) {
        primes[(int) j] = false;
      }
    }

    return IntStream.range(2, primes.length).filter(x -> primes[x]).boxed().toList();
  }
}
