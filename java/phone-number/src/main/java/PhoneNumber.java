public class PhoneNumber {
	String number;

	PhoneNumber(String s) {
		if (!s.chars().allMatch(ch -> Character.isDigit(ch) || " ()-.+".indexOf(ch) >= 0)) {
			throw new IllegalArgumentException(
					"Illegal character in phone number. Only digits, spaces, parentheses, hyphens or dots accepted.");
		}

		StringBuilder digits = s.chars().filter(Character::isDigit).mapToObj(ch -> (char) ch)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);

		int length = digits.length();
		if (length != 10 && length != 11) {
			throw new IllegalArgumentException("Number must be 10 or 11 digits");
		}

		if (length == 11 && digits.charAt(0) != '1') {
			throw new IllegalArgumentException("Can only have 11 digits if number starts with '1'");
		}

		number = digits.substring(length - 10, length);

		for (int index : new int[] { 0, 3 }) {
			char ch = number.charAt(index);

			if (!(ch >= '2' && ch <= '9')) {
				throw new IllegalArgumentException("Illegal Area Or Exchange Code. Only 2-9 are valid digits");
			}
		}
	}

	String getNumber() {
		return number;
	}
}