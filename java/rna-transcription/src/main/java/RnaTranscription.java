import static java.util.Map.entry;

import java.util.Map;
import java.util.stream.Collectors;

class RnaTranscription {
  static final Map<Character, Character> DNA_TO_RNA =
      Map.ofEntries(entry('G', 'C'), entry('C', 'G'), entry('T', 'A'), entry('A', 'U'));

  String transcribe(String dnaStrand) {
    if (dnaStrand.chars().anyMatch(dna -> !DNA_TO_RNA.containsKey((char) dna))) {
      throw new IllegalArgumentException("Invalid input");
    }

    return dnaStrand
        .chars()
        .mapToObj(dna -> DNA_TO_RNA.get((char) dna))
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}
