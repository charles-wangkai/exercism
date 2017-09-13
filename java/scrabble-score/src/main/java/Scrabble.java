import java.util.HashMap;
import java.util.Map;

class Scrabble {
	static Map<Character, Integer> letter2value;
	static {
		letter2value = new HashMap<Character, Integer>();

		letter2value.put('A', 1);
		letter2value.put('E', 1);
		letter2value.put('I', 1);
		letter2value.put('O', 1);
		letter2value.put('U', 1);
		letter2value.put('L', 1);
		letter2value.put('N', 1);
		letter2value.put('R', 1);
		letter2value.put('S', 1);
		letter2value.put('T', 1);

		letter2value.put('D', 2);
		letter2value.put('G', 2);

		letter2value.put('B', 3);
		letter2value.put('C', 3);
		letter2value.put('M', 3);
		letter2value.put('P', 3);

		letter2value.put('F', 4);
		letter2value.put('H', 4);
		letter2value.put('V', 4);
		letter2value.put('W', 4);
		letter2value.put('Y', 4);

		letter2value.put('K', 5);

		letter2value.put('J', 8);
		letter2value.put('X', 8);

		letter2value.put('Q', 10);
		letter2value.put('Z', 10);
	}

	String word;

	Scrabble(String word) {
		this.word = word;
	}

	int getScore() {
		return word.chars().map(Character::toUpperCase).map(ch -> letter2value.get((char) ch)).sum();
	}

}
