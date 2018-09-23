public class RotationalCipher {
	private static final int ALPHABET_SIZE = 26;

	private final int key;

	public RotationalCipher(int key) {
		this.key = key;
	}

	public String rotate(String s) {
		return s.chars().mapToObj(this::rotate)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	}

	private char rotate(int ch) {
		if (Character.isLowerCase(ch)) {
			return (char) ((ch - 'a' + key) % ALPHABET_SIZE + 'a');
		} else if (Character.isUpperCase(ch)) {
			return (char) ((ch - 'A' + key) % ALPHABET_SIZE + 'A');
		} else {
			return (char) ch;
		}
	}
}