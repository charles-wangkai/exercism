import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class CustomSet<T> {
  Set<T> set;

  CustomSet(List<T> elements) {
    set = new HashSet<>(elements);
  }

  @Override
  public boolean equals(Object obj) {
    @SuppressWarnings("unchecked")
    CustomSet<T> other = (CustomSet<T>) obj;

    return set.equals(other.set);
  }

  boolean isEmpty() {
    return set.isEmpty();
  }

  boolean contains(T element) {
    return set.contains(element);
  }

  boolean isSubset(CustomSet<T> other) {
    return set.containsAll(other.set);
  }

  boolean isDisjoint(CustomSet<T> other) {
    return set.stream().allMatch(element -> !other.contains(element));
  }

  void add(T element) {
    set.add(element);
  }

  CustomSet<T> getIntersection(CustomSet<T> other) {
    return new CustomSet<>(set.stream().filter(other.set::contains).toList());
  }

  CustomSet<T> getDifference(CustomSet<T> other) {
    return new CustomSet<>(set.stream().filter(element -> !other.set.contains(element)).toList());
  }

  CustomSet<T> getUnion(CustomSet<T> other) {
    return new CustomSet<>(Stream.concat(set.stream(), other.set.stream()).toList());
  }
}
