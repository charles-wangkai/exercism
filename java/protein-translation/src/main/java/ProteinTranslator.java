import static java.util.Map.entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ProteinTranslator {
  private static final int CODON_LENGTH = 3;
  private static Map<String, String> CODON_TO_PROTEIN =
      Map.ofEntries(
          entry("AUG", "Methionine"),
          entry("UUU", "Phenylalanine"),
          entry("UUC", "Phenylalanine"),
          entry("UUA", "Leucine"),
          entry("UUG", "Leucine"),
          entry("UCU", "Serine"),
          entry("UCC", "Serine"),
          entry("UCA", "Serine"),
          entry("UCG", "Serine"),
          entry("UAU", "Tyrosine"),
          entry("UAC", "Tyrosine"),
          entry("UGU", "Cysteine"),
          entry("UGC", "Cysteine"),
          entry("UGG", "Tryptophan"),
          entry("UAA", "STOP"),
          entry("UAG", "STOP"),
          entry("UGA", "STOP"));

  List<String> translate(String rnaSequence) {
    List<String> proteins = new ArrayList<>();
    for (int i = 0; i < rnaSequence.length(); i += CODON_LENGTH) {
      String protein = CODON_TO_PROTEIN.get(rnaSequence.substring(i, i + CODON_LENGTH));
      if (protein.equals("STOP")) {
        break;
      }

      proteins.add(protein);
    }

    return proteins;
  }
}
