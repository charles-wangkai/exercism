class RaindropConverter {

	String convert(int number) {
		StringBuilder result = new StringBuilder();
		if (number % 3 == 0) {
			result.append("Pling");
		}
		if (number % 5 == 0) {
			result.append("Plang");
		}
		if (number % 7 == 0) {
			result.append("Plong");
		}
		if (result.length() == 0) {
			result.append(number);
		}
		return result.toString();
	}

}
