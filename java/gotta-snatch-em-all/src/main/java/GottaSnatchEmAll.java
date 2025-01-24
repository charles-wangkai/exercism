import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class GottaSnatchEmAll {
  static Set<String> newCollection(List<String> cards) {
    return cards.stream().collect(Collectors.toSet());
  }

  static boolean addCard(String card, Set<String> collection) {
    return collection.add(card);
  }

  static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
    return hasExtra(myCollection, theirCollection) && hasExtra(theirCollection, myCollection);
  }

  static boolean hasExtra(Set<String> set1, Set<String> set2) {
    return set1.stream().anyMatch(x1 -> !set2.contains(x1));
  }

  static Set<String> commonCards(List<Set<String>> collections) {
    return collections.stream()
        .reduce((acc, x) -> acc.stream().filter(x::contains).collect(Collectors.toSet()))
        .orElse(Set.of());
  }

  static Set<String> allCards(List<Set<String>> collections) {
    return collections.stream()
        .reduce((acc, x) -> Stream.concat(acc.stream(), x.stream()).collect(Collectors.toSet()))
        .orElse(Set.of());
  }
}
