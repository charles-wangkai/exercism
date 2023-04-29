import java.util.List;
import java.util.stream.IntStream;

public class RelationshipComputer<T> {
  Relationship computeRelationship(List<T> A, List<T> B) {
    if (contains(B, A)) {
      return contains(A, B) ? Relationship.EQUAL : Relationship.SUBLIST;
    }

    return contains(A, B) ? Relationship.SUPERLIST : Relationship.UNEQUAL;
  }

  boolean contains(List<T> list, List<T> part) {
    return IntStream.rangeClosed(0, list.size() - part.size())
        .anyMatch(i -> list.subList(i, i + part.size()).equals(part));
  }
}
