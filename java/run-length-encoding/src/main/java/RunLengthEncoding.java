public class RunLengthEncoding {
	String encode(String s) {
		StringBuilder result = new StringBuilder();
		char prev = 0;
		int count = -1;
		for (int i = 0; i <= s.length(); i++) {
			if (i < s.length() && s.charAt(i) == prev) {
				count++;
			} else {
				if (count > 0) {
					if (count != 1) {
						result.append(count);
					}
					result.append(prev);
				}

				if (i < s.length()) {
					prev = s.charAt(i);
					count = 1;
				}
			}
		}
		return result.toString();
	}

	String decode(String s) {
		StringBuilder result = new StringBuilder();
		int index = 0;
		while (index < s.length()) {
			int endIndex = index;
			while (endIndex < s.length() && Character.isDigit(s.charAt(endIndex))) {
				endIndex++;
			}

			int count;
			if (endIndex == index) {
				count = 1;
			} else {
				count = Integer.parseInt(s.substring(index, endIndex));
			}

			char letter = s.charAt(endIndex);
			for (int i = 0; i < count; i++) {
				result.append(letter);
			}

			index = endIndex + 1;
		}
		return result.toString();
	}
}