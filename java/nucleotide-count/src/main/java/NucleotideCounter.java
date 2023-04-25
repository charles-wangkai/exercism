import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class NucleotideCounter {
  static final char[] SYMBOLS = {'A', 'C', 'G', 'T'};

  String dna;

  NucleotideCounter(String dna) {
    if (!dna.chars().allMatch(c -> isValidSymbol((char) c))) {
      throw new IllegalArgumentException();
    }

    this.dna = dna;
  }

  boolean isValidSymbol(char c) {
    return IntStream.range(0, SYMBOLS.length).anyMatch(i -> c == SYMBOLS[i]);
  }

  Map<Character, Integer> nucleotideCounts() {
    Map<Character, Integer> symbolToCount = new HashMap<>();
    for (char symbol : SYMBOLS) {
      symbolToCount.put(symbol, count(symbol));
    }

    return symbolToCount;
  }

  int count(char symbol) {
    if (IntStream.range(0, SYMBOLS.length).allMatch(i -> symbol != SYMBOLS[i])) {
      throw new IllegalArgumentException();
    }

    return (int) dna.chars().filter(c -> c == symbol).count();
  }
}
