public class CollatzCalculator {
  int computeStepCount(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("Only natural numbers are allowed");
    }

    int step = 0;
    while (n != 1) {
      n = (n % 2 == 0) ? (n / 2) : (3 * n + 1);
      ++step;
    }

    return step;
  }
}
