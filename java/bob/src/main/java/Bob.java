import java.util.stream.IntStream;

public class Bob {
	String hey(String s) {
		if (s.chars().filter(ch -> !Character.isWhitespace(ch)).count() == 0) {
			return "Fine. Be that way!";
		} else if (generateCandidateStream(s).count() > 0
				&& generateCandidateStream(s).allMatch(Character::isUpperCase)) {
			if (isQuestion(s)) {
				return "Calm down, I know what I'm doing!";
			} else {
				return "Whoa, chill out!";
			}
		} else if (isQuestion(s)) {
			return "Sure.";
		} else {
			return "Whatever.";
		}
	}

	boolean isQuestion(String s) {
		return s.replaceAll("\\s", "").endsWith("?");
	}

	IntStream generateCandidateStream(String s) {
		return s.chars().filter(Character::isLetter);
	}
}