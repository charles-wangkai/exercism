import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class IsogramChecker {
	boolean isIsogram(String s) {
		return createCandidateStream(s).map(Character::toLowerCase)
				.collect(HashSet<Integer>::new, Set::add, Set::addAll).size() == createCandidateStream(s).count();
	}

	IntStream createCandidateStream(String s) {
		return s.chars().filter(ch -> ch != ' ' && ch != '-');
	}
}