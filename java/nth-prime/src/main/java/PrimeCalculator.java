public class PrimeCalculator {
  int nth(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException();
    }

    int prime = 1;
    for (int i = 0; i < n; ++i) {
      prime = nextPrime(prime);
    }

    return prime;
  }

  int nextPrime(int x) {
    while (true) {
      ++x;
      if (isPrime(x)) {
        return x;
      }
    }
  }

  boolean isPrime(int x) {
    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
}
