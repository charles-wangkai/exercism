import java.util.Arrays;
import java.util.stream.Collectors;

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
		return word.substring(0, 1);
	}
}
