import java.util.Arrays;

class Acronym {
	String phrase;

	Acronym(String phrase) {
		this.phrase = phrase;
	}

	String get() {
		return Arrays.stream(phrase.split(" |-")).map(word -> word.charAt(0)).map(Character::toUpperCase)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	}

}