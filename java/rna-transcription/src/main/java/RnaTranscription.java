import static java.util.Map.entry;

import java.util.Map;

class RnaTranscription {
  static final Map<Character, Character> DNA_TO_RNA =
      Map.ofEntries(entry('G', 'C'), entry('C', 'G'), entry('T', 'A'), entry('A', 'U'));

  String transcribe(String dnaStrand) {
    StringBuilder result = new StringBuilder();
    for (char dna : dnaStrand.toCharArray()) {
      if (!DNA_TO_RNA.containsKey(dna)) {
        throw new IllegalArgumentException("Invalid input");
      }

      result.append(DNA_TO_RNA.get(dna));
    }

    return result.toString();
  }
}
