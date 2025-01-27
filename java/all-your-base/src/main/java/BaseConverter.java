import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BaseConverter {
  int base;
  int[] digits;

  BaseConverter(int base, int[] digits) {
    checkBase(base);

    if (Arrays.stream(digits).anyMatch(digit -> digit >= base)) {
      throw new IllegalArgumentException("All digits must be strictly less than the base.");
    }
    if (Arrays.stream(digits).anyMatch(digit -> digit < 0)) {
      throw new IllegalArgumentException("Digits may not be negative.");
    }

    this.base = base;
    this.digits = digits;
  }

  int[] convertToBase(int targetBase) {
    checkBase(targetBase);

    int value = 0;
    for (int digit : digits) {
      value = value * base + digit;
    }

    List<Integer> targetDigits = new ArrayList<>();
    while (value != 0) {
      targetDigits.add(value % targetBase);
      value /= targetBase;
    }
    if (targetDigits.isEmpty()) {
      targetDigits.add(0);
    }
    Collections.reverse(targetDigits);

    return targetDigits.stream().mapToInt(Integer::intValue).toArray();
  }

  void checkBase(int b) {
    if (b < 2) {
      throw new IllegalArgumentException("Bases must be at least 2.");
    }
  }
}
