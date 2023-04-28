import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Poker {
  List<String> hands;

  Poker(List<String> hands) {
    this.hands = hands;
  }

  List<String> getBestHands() {
    Category[] categories =
        hands.stream()
            .map(
                hand -> {
                  Card[] cards =
                      Arrays.stream(hand.split(" "))
                          .map(
                              s -> {
                                char suit = s.charAt(s.length() - 1);

                                int value;
                                char c = s.charAt(s.length() - 2);
                                if (c >= '2' && c <= '9') {
                                  value = c - '0';
                                } else if (c == '0') {
                                  value = 10;
                                } else if (c == 'J') {
                                  value = 11;
                                } else if (c == 'Q') {
                                  value = 12;
                                } else if (c == 'K') {
                                  value = 13;
                                } else {
                                  value = 14;
                                }

                                return new Card(suit, value);
                              })
                          .sorted(Comparator.comparing(Card::value).reversed())
                          .toArray(Card[]::new);

                  int rank;
                  int[] candidates;
                  if (isStraightFlush(cards)) {
                    rank = 1;

                    if (isAceToFive(cards)) {
                      candidates = new int[] {5};
                    } else {
                      candidates = new int[] {cards[0].value()};
                    }
                  } else if (isSquare(cards)) {
                    rank = 2;

                    if (cards[0].value() == cards[3].value()) {
                      candidates = new int[] {cards[0].value(), cards[4].value()};
                    } else {
                      candidates = new int[] {cards[4].value(), cards[0].value()};
                    }
                  } else if (isFull(cards)) {
                    rank = 3;

                    if (cards[0].value() == cards[2].value()
                        && cards[3].value() == cards[4].value()) {
                      candidates = new int[] {cards[0].value(), cards[3].value()};
                    } else {
                      candidates = new int[] {cards[2].value(), cards[0].value()};
                    }
                  } else if (isFlush(cards)) {
                    rank = 4;

                    candidates =
                        new int[] {
                          cards[0].value(),
                          cards[1].value(),
                          cards[2].value(),
                          cards[3].value(),
                          cards[4].value()
                        };
                  } else if (isStraight(cards)) {
                    rank = 5;

                    if (isAceToFive(cards)) {
                      candidates = new int[] {5};
                    } else {
                      candidates = new int[] {cards[0].value()};
                    }
                  } else if (isThree(cards)) {
                    rank = 6;

                    if (cards[0].value() == cards[2].value()) {
                      candidates = new int[] {cards[0].value(), cards[3].value(), cards[4].value()};
                    } else if (cards[1].value() == cards[3].value()) {
                      candidates = new int[] {cards[1].value(), cards[0].value(), cards[4].value()};
                    } else {
                      candidates = new int[] {cards[2].value(), cards[0].value(), cards[1].value()};
                    }
                  } else if (isDoublePair(cards)) {
                    rank = 7;

                    if (cards[0].value() == cards[1].value()
                        && cards[2].value() == cards[3].value()) {
                      candidates = new int[] {cards[0].value(), cards[2].value(), cards[4].value()};
                    } else if (cards[0].value() == cards[1].value()
                        && cards[3].value() == cards[4].value()) {
                      candidates = new int[] {cards[0].value(), cards[3].value(), cards[2].value()};
                    } else {
                      candidates = new int[] {cards[1].value(), cards[3].value(), cards[0].value()};
                    }
                  } else if (isOnePair(cards)) {
                    rank = 8;

                    if (cards[0].value() == cards[1].value()) {
                      candidates =
                          new int[] {
                            cards[0].value(), cards[2].value(), cards[3].value(), cards[4].value()
                          };
                    } else if (cards[1].value() == cards[2].value()) {
                      candidates =
                          new int[] {
                            cards[1].value(), cards[0].value(), cards[3].value(), cards[4].value()
                          };
                    } else if (cards[2].value() == cards[3].value()) {
                      candidates =
                          new int[] {
                            cards[2].value(), cards[0].value(), cards[1].value(), cards[4].value()
                          };
                    } else {
                      candidates =
                          new int[] {
                            cards[3].value(), cards[0].value(), cards[1].value(), cards[2].value()
                          };
                    }
                  } else {
                    rank = 9;

                    candidates =
                        new int[] {
                          cards[0].value(),
                          cards[1].value(),
                          cards[2].value(),
                          cards[3].value(),
                          cards[4].value()
                        };
                  }

                  return new Category(rank, candidates);
                })
            .toArray(Category[]::new);

    Category bestCategory = Arrays.stream(categories).min(Comparator.naturalOrder()).get();

    return IntStream.range(0, hands.size())
        .filter(i -> categories[i].compareTo(bestCategory) == 0)
        .mapToObj(hands::get)
        .toList();
  }

  boolean isFull(Card[] cards) {
    return (cards[0].value() == cards[2].value() && cards[3].value() == cards[4].value())
        || (cards[0].value() == cards[1].value() && cards[2].value() == cards[4].value());
  }

  boolean isThree(Card[] cards) {
    return cards[0].value() == cards[2].value()
        || cards[1].value() == cards[3].value()
        || cards[2].value() == cards[4].value();
  }

  boolean isAceToFive(Card[] cards) {
    return cards[0].value() == 14
        && cards[1].value() == 5
        && cards[2].value() == 4
        && cards[3].value() == 3
        && cards[4].value() == 2;
  }

  boolean isStraight(Card[] cards) {
    return IntStream.rangeClosed(0, 3).allMatch(i -> cards[i].value() - cards[i + 1].value() == 1)
        || isAceToFive(cards);
  }

  boolean isSquare(Card[] cards) {
    return cards[0].value() == cards[3].value() || cards[1].value() == cards[4].value();
  }

  boolean isFlush(Card[] cards) {
    return Arrays.stream(cards).map(card -> card.suit()).distinct().count() == 1;
  }

  boolean isStraightFlush(Card[] cards) {
    return isStraight(cards) && isFlush(cards);
  }

  boolean isOnePair(Card[] cards) {
    return IntStream.rangeClosed(0, 3).anyMatch(i -> cards[i].value() == cards[i + 1].value());
  }

  boolean isDoublePair(Card[] cards) {
    return (cards[0].value() == cards[1].value() && cards[2].value() == cards[3].value())
        || (cards[0].value() == cards[1].value() && cards[3].value() == cards[4].value())
        || (cards[1].value() == cards[2].value() && cards[3].value() == cards[4].value());
  }
}

record Category(int rank, int[] candidates) implements Comparable<Category> {
  @Override
  public int compareTo(Category other) {
    if (rank() != other.rank()) {
      return Integer.compare(rank(), other.rank());
    }

    for (int i = 0; i < candidates().length; ++i) {
      if (candidates()[i] != other.candidates()[i]) {
        return -Integer.compare(candidates()[i], other.candidates()[i]);
      }
    }

    return 0;
  }
}

record Card(char suit, int value) {}
