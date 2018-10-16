import java.util.HashMap;
import java.util.Map;

public class NucleotideCounter {
	static final char[] SYMBOLS = { 'A', 'C', 'G', 'T' };

	String dna;

	NucleotideCounter(String dna) {
		if (!dna.chars().allMatch(this::isValidSymbol)) {
			throw new IllegalArgumentException();
		}

		this.dna = dna;
	}

	boolean isValidSymbol(int ch) {
		for (char symbol : SYMBOLS) {
			if (ch == symbol) {
				return true;
			}
		}
		return false;
	}

	Map<Character, Integer> nucleotideCounts() {
		Map<Character, Integer> symbol2count = new HashMap<Character, Integer>();
		for (char symbol : SYMBOLS) {
			symbol2count.put(symbol, count(symbol));
		}
		return symbol2count;
	}

	int count(char symbol) {
		if (new String(SYMBOLS).indexOf(symbol) < 0) {
			throw new IllegalArgumentException();
		}

		return (int) dna.chars().filter(ch -> ch == symbol).count();
	}
}