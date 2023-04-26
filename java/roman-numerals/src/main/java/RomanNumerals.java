public class RomanNumerals {
  static final int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
  static final String[] SYMBOLS = {
    "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
  };

  int arabicNumeral;

  RomanNumerals(int arabicNumeral) {
    this.arabicNumeral = arabicNumeral;
  }

  String getRomanNumeral() {
    StringBuilder roman = new StringBuilder();
    int rest = arabicNumeral;
    for (int i = 0; i < VALUES.length; ++i) {
      while (rest >= VALUES[i]) {
        roman.append(SYMBOLS[i]);
        rest -= VALUES[i];
      }
    }

    return roman.toString();
  }
}
