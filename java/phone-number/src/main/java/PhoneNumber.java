public class PhoneNumber {
	String number;

	PhoneNumber(String s) {
		for (char ch : s.toCharArray()) {
			if (!Character.isDigit(ch) && " ()-.+".indexOf(ch) == -1) {
				if (Character.isLetter(ch)) {
					throw new IllegalArgumentException("letters not permitted");
				} else {
					throw new IllegalArgumentException("punctuations not permitted");
				}
			}
		}

		StringBuilder digits = s.chars().filter(Character::isDigit).mapToObj(ch -> (char) ch)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);

		int length = digits.length();
		if (length > 11) {
			throw new IllegalArgumentException("more than 11 digits");
		}
		if (length != 10 && length != 11) {
			throw new IllegalArgumentException("incorrect number of digits");
		}

		if (length == 11 && digits.charAt(0) != '1') {
			throw new IllegalArgumentException("11 digits must start with 1");
		}

		number = digits.substring(length - 10, length);

		if (number.charAt(0) == '0') {
			throw new IllegalArgumentException("area code cannot start with zero");
		}
		if (number.charAt(0) == '1') {
			throw new IllegalArgumentException("area code cannot start with one");
		}

		if (number.charAt(3) == '0') {
			throw new IllegalArgumentException("exchange code cannot start with zero");
		}
		if (number.charAt(3) == '1') {
			throw new IllegalArgumentException("exchange code cannot start with one");
		}
	}

	String getNumber() {
		return number;
	}
}