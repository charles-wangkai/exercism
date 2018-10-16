import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class VariableLengthQuantity {
	List<String> encode(List<Long> numbers) {
		return numbers.stream().flatMap(number -> encodeOneNumber(number).stream()).collect(Collectors.toList());
	}

	List<String> encodeOneNumber(long number) {
		List<String> result = new ArrayList<String>();
		String s = Long.toBinaryString(number);
		for (int i = 0; i < s.length(); i += 7) {
			result.add(0,
					"0x" + Integer.toHexString(
							Integer.parseInt(s.substring(Math.max(0, s.length() - i - 7), s.length() - i), 2)
									+ ((i == 0) ? 0 : (1 << 7))));
		}
		return result;
	}

	List<String> decode(List<Long> bytes) {
		if (bytes.get(bytes.size() - 1) >= 1 << 7) {
			throw new IllegalArgumentException("Invalid variable-length quantity encoding");
		}

		List<String> result = new ArrayList<String>();
		int beginIndex = 0;
		for (int i = 0; i < bytes.size(); i++) {
			if (bytes.get(i) < 1 << 7) {
				result.add(decodeOneNumber(bytes.subList(beginIndex, i + 1)));
				beginIndex = i + 1;
			}
		}
		return result;
	}

	String decodeOneNumber(List<Long> bytes) {
		return "0x" + Long.toHexString(Long.parseLong(String.join("",
				bytes.stream().map(b -> String.format("%7s", Long.toBinaryString(b % (1 << 7))).replace(' ', '0'))
						.collect(Collectors.toList())),
				2));
	}
}