import java.util.ArrayList;
import java.util.List;

public class Crypto {
	String s;

	Crypto(String s) {
		this.s = s;
	}

	String getNormalizedPlaintext() {
		return s.toLowerCase().chars().filter(Character::isLetterOrDigit).mapToObj(ch -> (char) ch)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	}

	int getSquareSize() {
		return (int) Math.ceil(Math.sqrt(getNormalizedPlaintext().length()));
	}

	List<String> getPlaintextSegments() {
		List<String> segments = new ArrayList<String>();
		String normalizedPlaintext = getNormalizedPlaintext();
		int squareSize = getSquareSize();
		for (int i = 0; i < normalizedPlaintext.length(); i += squareSize) {
			segments.add(normalizedPlaintext.substring(i, Math.min(i + squareSize, normalizedPlaintext.length())));
		}
		return segments;
	}

	String getCipherText() {
		StringBuilder cipherText = new StringBuilder();
		int squareSize = getSquareSize();
		List<String> segments = getPlaintextSegments();
		for (int i = 0; i < squareSize; i++) {
			for (String segment : segments) {
				if (i < segment.length()) {
					cipherText.append(segment.charAt(i));
				}
			}
		}
		return cipherText.toString();
	}

	String getNormalizedCipherText() {
		StringBuilder normalizedCipherText = new StringBuilder();
		int squareSize = getSquareSize();
		List<String> segments = getPlaintextSegments();
		for (int i = 0; i < squareSize; i++) {
			if (normalizedCipherText.length() != 0) {
				normalizedCipherText.append(" ");
			}

			for (String segment : segments) {
				if (i < segment.length()) {
					normalizedCipherText.append(segment.charAt(i));
				}
			}
		}
		return normalizedCipherText.toString();
	}
}
