import java.util.List;
import java.util.stream.IntStream;

public class RelationshipComputer<T> {
	Relationship computeRelationship(List<T> A, List<T> B) {
		if (contains(B, A)) {
			if (contains(A, B)) {
				return Relationship.EQUAL;
			} else {
				return Relationship.SUBLIST;
			}
		} else {
			if (contains(A, B)) {
				return Relationship.SUPERLIST;
			} else {
				return Relationship.UNEQUAL;
			}
		}
	}

	boolean contains(List<T> list, List<T> part) {
		return IntStream.rangeClosed(0, list.size() - part.size())
				.anyMatch(i -> isSame(list.subList(i, i + part.size()), part));
	}

	boolean isSame(List<T> list1, List<T> list2) {
		return IntStream.range(0, list1.size()).allMatch(i -> list1.get(i).equals(list2.get(i)));
	}
}