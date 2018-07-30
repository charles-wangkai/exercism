import java.util.stream.Collectors;

public class PangramChecker {

	public boolean isPangram(String input) {
		return input.chars().filter(Character::isLetter).map(Character::toLowerCase).boxed().collect(Collectors.toSet())
				.size() == 26;
	}

}