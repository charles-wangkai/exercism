import java.util.stream.IntStream;

class ArmstrongNumbers {
  boolean isArmstrongNumber(int numberToCheck) {
    return numberToCheck
        == String.valueOf(numberToCheck)
            .chars()
            .map(c -> pow(c - '0', String.valueOf(numberToCheck).length()))
            .sum();
  }

  int pow(int base, int exponent) {
    return IntStream.range(0, exponent).reduce(1, (acc, i) -> acc * base);
  }
}
