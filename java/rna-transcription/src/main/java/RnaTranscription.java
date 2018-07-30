import java.util.HashMap;
import java.util.Map;

class RnaTranscription {

	String transcribe(String dnaStrand) {
		Map<Character, Character> dna2rna = new HashMap<Character, Character>();
		dna2rna.put('G', 'C');
		dna2rna.put('C', 'G');
		dna2rna.put('T', 'A');
		dna2rna.put('A', 'U');

		StringBuilder result = new StringBuilder();
		for (char dna : dnaStrand.toCharArray()) {
			if (!dna2rna.containsKey(dna)) {
				throw new IllegalArgumentException("Invalid input");
			}

			result.append(dna2rna.get(dna));
		}
		return result.toString();
	}

}