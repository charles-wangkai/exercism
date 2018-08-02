import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Robot {
	static Random random = new Random();
	static Set<String> names = new HashSet<String>();

	String name;

	String getName() {
		if (name == null) {
			do {
				name = String.format("%c%c%d%d%d", generateLetter(), generateLetter(), generateDigit(), generateDigit(),
						generateDigit());
			} while (!names.add(name));
		}
		return name;
	}

	void reset() {
		names.remove(name);
		name = null;
	}

	char generateLetter() {
		return (char) (random.nextInt(26) + 'A');
	}

	int generateDigit() {
		return random.nextInt(10);
	}
}