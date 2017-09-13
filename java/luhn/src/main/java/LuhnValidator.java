class LuhnValidator {

	boolean isValid(String candidate) {
		candidate = candidate.replaceAll(" ", "");
		if (candidate.length() <= 1 || !candidate.chars().allMatch(Character::isDigit)) {
			return false;
		}

		int sum = 0;
		boolean needDouble = false;
		for (int i = candidate.length() - 1; i >= 0; i--) {
			int digit = candidate.charAt(i) - '0';
			sum += needDouble ? doDouble(digit) : digit;

			needDouble = !needDouble;
		}
		return sum % 10 == 0;
	}

	int doDouble(int digit) {
		int result = digit * 2;
		if (result > 9) {
			result -= 9;
		}
		return result;
	}

}
