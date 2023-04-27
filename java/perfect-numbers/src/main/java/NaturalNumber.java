import java.util.stream.IntStream;

class NaturalNumber {
  int number;

  NaturalNumber(int number) {
    if (number <= 0) {
      throw new IllegalArgumentException("You must supply a natural number (positive integer)");
    }

    this.number = number;
  }

  Classification getClassification() {
    int aliquotSum = computeAliquotSum();
    if (aliquotSum < number) {
      return Classification.DEFICIENT;
    }
    if (aliquotSum > number) {
      return Classification.ABUNDANT;
    }

    return Classification.PERFECT;
  }

  int computeAliquotSum() {
    return IntStream.range(1, number).filter(x -> number % x == 0).sum();
  }
}
