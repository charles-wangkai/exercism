public class SquareRoot {
  public int squareRoot(int radicand) {
    int result = -1;
    int lower = 1;
    int upper = Integer.MAX_VALUE;
    while (lower <= upper) {
      int middle = lower + (upper - lower) / 2;
      if ((long) middle * middle <= radicand) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }
}
