import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FoodChain {
	static final String[] ANIMALS = { null, "fly", "spider", "bird", "cat", "dog", "goat", "cow", "horse" };
	static final String[] COMMENTS = { null, null, "It wriggled and jiggled and tickled inside her.",
			"How absurd to swallow a bird!", "Imagine that, to swallow a cat!", "What a hog, to swallow a dog!",
			"Just opened her throat and swallowed a goat!", "I don't know how she swallowed a cow!" };
	static final String[] PARTS = { null, null, "She swallowed the spider to catch the fly.",
			"She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
			"She swallowed the cat to catch the bird.", "She swallowed the dog to catch the cat.",
			"She swallowed the goat to catch the dog.", "She swallowed the cow to catch the goat." };

	String verse(int verse) {
		StringBuilder result = new StringBuilder(
				String.format("I know an old lady who swallowed a %s.", ANIMALS[verse]));
		if (verse != 1 && verse != 8) {
			result.append("\n");
			result.append(COMMENTS[verse]);
		}
		if (verse != 8) {
			for (int i = verse; i >= 2; i--) {
				result.append("\n");
				result.append(PARTS[i]);
			}
		}
		result.append("\n");
		result.append(
				verse == 8 ? "She's dead, of course!" : "I don't know why she swallowed the fly. Perhaps she'll die.");
		return result.toString();
	}

	String verses(int startVerse, int endVerse) {
		return String.join("\n\n",
				IntStream.rangeClosed(startVerse, endVerse).mapToObj(this::verse).collect(Collectors.toList()));
	}
}
