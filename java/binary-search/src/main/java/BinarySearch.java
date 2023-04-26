import java.util.List;

public class BinarySearch<T extends Comparable<? super T>> {
  List<T> elements;

  BinarySearch(List<T> elements) {
    this.elements = elements;
  }

  int indexOf(T target) throws ValueNotFoundException {
    int lowerIndex = 0;
    int upperIndex = elements.size() - 1;
    while (lowerIndex <= upperIndex) {
      int middleIndex = (lowerIndex + upperIndex) / 2;
      int cmp = elements.get(middleIndex).compareTo(target);
      if (cmp == 0) {
        return middleIndex;
      }

      if (cmp < 0) {
        lowerIndex = middleIndex + 1;
      } else {
        upperIndex = middleIndex - 1;
      }
    }

    throw new ValueNotFoundException("Value not in array");
  }
}
