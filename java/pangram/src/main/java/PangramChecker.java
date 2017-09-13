import java.util.HashSet;
import java.util.Set;

public class PangramChecker {

	public boolean isPangram(String input) {
		return input.chars().filter(Character::isLetter).map(Character::toLowerCase)
				.collect(HashSet<Integer>::new, Set::add, Set::addAll).size() == 26;
	}

}
