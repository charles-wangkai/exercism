import java.util.stream.IntStream;

public class Bob {
	String hey(String s) {
		if (s.chars().filter(ch -> ch != ' ').count() == 0) {
			return "Fine. Be that way!";
		} else if (generateCandidateStream(s).count() > 0
				&& generateCandidateStream(s).allMatch(Character::isUpperCase)) {
			return "Whoa, chill out!";
		} else if (s.endsWith("?")) {
			return "Sure.";
		} else {
			return "Whatever.";
		}
	}

	IntStream generateCandidateStream(String s) {
		return s.chars().filter(Character::isLetter);
	}
}
