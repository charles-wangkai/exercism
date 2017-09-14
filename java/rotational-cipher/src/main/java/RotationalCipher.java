public class RotationalCipher {
	int key;

	RotationalCipher(int key) {
		this.key = key;
	}

	String rotate(String s) {
		return s.chars().mapToObj(this::rotate)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	}

	char rotate(int ch) {
		if (Character.isLowerCase(ch)) {
			return (char) ((ch - 'a' + key) % 26 + 'a');
		} else if (Character.isUpperCase(ch)) {
			return (char) ((ch - 'A' + key) % 26 + 'A');
		} else {
			return (char) ch;
		}
	}
}
