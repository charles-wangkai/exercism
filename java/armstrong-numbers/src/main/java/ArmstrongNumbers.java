class ArmstrongNumbers {
  boolean isArmstrongNumber(int numberToCheck) {
    return numberToCheck
        == String.valueOf(numberToCheck)
            .chars()
            .map(c -> pow(c - '0', String.valueOf(numberToCheck).length()))
            .sum();
  }

  int pow(int base, int exponent) {
    int result = 1;
    for (int i = 0; i < exponent; ++i) {
      result *= base;
    }

    return result;
  }
}
