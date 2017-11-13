import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ProteinTranslator {
	private static final int CODON_LENGTH = 3;
	private static Map<String, String> CODON2PROTEIN = new HashMap<String, String>();
	static {
		CODON2PROTEIN.put("AUG", "Methionine");
		CODON2PROTEIN.put("UUU", "Phenylalanine");
		CODON2PROTEIN.put("UUC", "Phenylalanine");
		CODON2PROTEIN.put("UUA", "Leucine");
		CODON2PROTEIN.put("UUG", "Leucine");
		CODON2PROTEIN.put("UCU", "Serine");
		CODON2PROTEIN.put("UCC", "Serine");
		CODON2PROTEIN.put("UCA", "Serine");
		CODON2PROTEIN.put("UCG", "Serine");
		CODON2PROTEIN.put("UAU", "Tyrosine");
		CODON2PROTEIN.put("UAC", "Tyrosine");
		CODON2PROTEIN.put("UGU", "Cysteine");
		CODON2PROTEIN.put("UGC", "Cysteine");
		CODON2PROTEIN.put("UGG", "Tryptophan");
		CODON2PROTEIN.put("UAA", "STOP");
		CODON2PROTEIN.put("UAG", "STOP");
		CODON2PROTEIN.put("UGA", "STOP");
	}

	List<String> translate(String rnaSequence) {
		List<String> proteins = new ArrayList<String>();
		for (int i = 0; i < rnaSequence.length(); i += CODON_LENGTH) {
			String codon = rnaSequence.substring(i, i + CODON_LENGTH);
			String protein = CODON2PROTEIN.get(codon);
			if (protein.equals("STOP")) {
				break;
			}
			proteins.add(protein);
		}
		return proteins;
	}
}