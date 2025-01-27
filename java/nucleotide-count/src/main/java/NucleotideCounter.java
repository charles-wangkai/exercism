import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NucleotideCounter {
  static final String SYMBOLS = "ACGT";

  String dna;

  NucleotideCounter(String dna) {
    if (dna.chars().anyMatch(c -> SYMBOLS.indexOf(c) == -1)) {
      throw new IllegalArgumentException();
    }

    this.dna = dna;
  }

  Map<Character, Integer> nucleotideCounts() {
    return IntStream.range(0, SYMBOLS.length())
        .boxed()
        .collect(
            Collectors.toMap(
                i -> SYMBOLS.charAt(i),
                i -> (int) dna.chars().filter(c -> c == SYMBOLS.charAt(i)).count()));
  }
}
