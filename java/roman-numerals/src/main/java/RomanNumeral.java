public class RomanNumeral {
	static final int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
	static final String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

	int arabicNumeral;

	RomanNumeral(int arabicNumeral) {
		this.arabicNumeral = arabicNumeral;
	}

	String getRomanNumeral() {
		StringBuilder roman = new StringBuilder();
		int remain = arabicNumeral;
		for (int i = 0; i < values.length; i++) {
			while (remain >= values[i]) {
				roman.append(symbols[i]);
				remain -= values[i];
			}
		}
		return roman.toString();
	}
}