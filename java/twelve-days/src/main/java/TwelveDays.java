import java.util.stream.Collectors;
import java.util.stream.IntStream;

class TwelveDays {
	static final String[] SEQUENCES = { null, "first", "second", "third", "fourth", "fifth", "sixth", "seventh",
			"eighth", "ninth", "tenth", "eleventh", "twelfth" };
	static final String[] GIFTS = { null, "a Partridge in a Pear Tree", "two Turtle Doves", "three French Hens",
			"four Calling Birds", "five Gold Rings", "six Geese-a-Laying", "seven Swans-a-Swimming",
			"eight Maids-a-Milking", "nine Ladies Dancing", "ten Lords-a-Leaping", "eleven Pipers Piping",
			"twelve Drummers Drumming" };

	String verse(int verseNumber) {
		StringBuilder result = new StringBuilder(
				String.format("On the %s day of Christmas my true love gave to me: ", SEQUENCES[verseNumber]));
		for (int i = verseNumber; i >= 1; i--) {
			if (i < verseNumber) {
				result.append(", ");
			}
			if (verseNumber > 1 && i == 1) {
				result.append("and ");
			}
			result.append(GIFTS[i]);
		}
		result.append(".\n");
		return result.toString();
	}

	String verses(int startVerse, int endVerse) {
		return String.join("\n",
				IntStream.rangeClosed(startVerse, endVerse).mapToObj(this::verse).collect(Collectors.toList()));
	}

	String sing() {
		return verses(1, 12);
	}
}