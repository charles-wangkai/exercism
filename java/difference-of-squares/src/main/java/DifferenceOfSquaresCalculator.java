import java.util.stream.IntStream;

public class DifferenceOfSquaresCalculator {
  int computeSumOfSquaresTo(int n) {
    return IntStream.rangeClosed(1, n).map(x -> x * x).sum();
  }

  int computeSquareOfSumTo(int n) {
    int sum = IntStream.rangeClosed(1, n).sum();

    return sum * sum;
  }

  int computeDifferenceOfSquares(int n) {
    return computeSquareOfSumTo(n) - computeSumOfSquaresTo(n);
  }
}
