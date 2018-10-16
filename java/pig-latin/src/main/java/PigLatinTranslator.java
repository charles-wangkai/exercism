import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PigLatinTranslator {
	static final String[] VOWELS = { "yt", "xr", "a", "e", "i", "o", "u" };
	static final String[] MULTI_CONSONANTS = { "thr", "sch", "ch", "qu", "th" };

	String translate(String english) {
		return String.join(" ",
				Arrays.stream(english.split(" ")).map(this::translateWord).collect(Collectors.toList()));
	}

	String translateWord(String word) {
		if (startsWithVowel(word)) {
			return word + "ay";
		}

		String firstConsonant = getFirstConsonant(word);
		return word.substring(firstConsonant.length()) + firstConsonant + "ay";
	}

	boolean startsWithVowel(String word) {
		for (String vowel : VOWELS) {
			if (word.startsWith(vowel)) {
				return true;
			}
		}
		return false;
	}

	String getFirstConsonant(String word) {
		for (String multiConsonant : MULTI_CONSONANTS) {
			if (word.startsWith(multiConsonant)) {
				return multiConsonant;
			}
		}
		if (word.substring(1).startsWith("qu")) {
			return word.substring(0, 3);
		}

		int yIndex = word.indexOf('y');
		if (yIndex > 0 && IntStream.range(0, yIndex).allMatch(i -> !isSingleVowel(word.charAt(i)))) {
			return word.substring(0, yIndex);
		}

		return word.substring(0, 1);
	}

	boolean isSingleVowel(char ch) {
		return Arrays.stream(VOWELS).anyMatch(vowel -> vowel.equals(String.valueOf(ch)));
	}
}