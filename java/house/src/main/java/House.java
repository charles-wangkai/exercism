import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class House {
	static final String[] PARTS = { "the house that Jack built.", "the malt\nthat lay in ", "the rat\nthat ate ",
			"the cat\nthat killed ", "the dog\nthat worried ", "the cow with the crumpled horn\nthat tossed ",
			"the maiden all forlorn\nthat milked ", "the man all tattered and torn\nthat kissed ",
			"the priest all shaven and shorn\nthat married ", "the rooster that crowed in the morn\nthat woke ",
			"the farmer sowing his corn\nthat kept ", "the horse and the hound and the horn\nthat belonged to " };

	String verse(int verse) {
		StringBuilder result = new StringBuilder("This is ");
		for (int i = verse - 1; i >= 0; i--) {
			result.append(PARTS[i]);
		}
		return result.toString();
	}

	String verses(int startVerse, int endVerse) {
		return String.join("\n\n",
				IntStream.rangeClosed(startVerse, endVerse).mapToObj(this::verse).collect(Collectors.toList()));
	}

	String sing() {
		return verses(1, 12);
	}
}
