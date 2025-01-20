import static java.util.Map.entry;

import java.util.Map;

public class Blackjack {
  static final Map<String, Integer> CARD_TO_VALUE =
      Map.ofEntries(
          entry("ace", 11),
          entry("two", 2),
          entry("three", 3),
          entry("four", 4),
          entry("five", 5),
          entry("six", 6),
          entry("seven", 7),
          entry("eight", 8),
          entry("nine", 9),
          entry("ten", 10),
          entry("jack", 10),
          entry("queen", 10),
          entry("king", 10),
          entry("other", 0));

  public int parseCard(String card) {
    return CARD_TO_VALUE.get(card);
  }

  public boolean isBlackjack(String card1, String card2) {
    return parseCard(card1) + parseCard(card2) == 21;
  }

  public String largeHand(boolean isBlackjack, int dealerScore) {
    if (!isBlackjack) {
      return "P";
    }

    return (dealerScore < 10) ? "W" : "S";
  }

  public String smallHand(int handScore, int dealerScore) {
    if (handScore >= 17) {
      return "S";
    }
    if (handScore <= 11) {
      return "H";
    }

    return (dealerScore >= 7) ? "H" : "S";
  }

  // FirstTurn returns the semi-optimal decision for the first turn, given the cards of the player
  // and the dealer.
  // This function is already implemented and does not need to be edited. It pulls the other
  // functions together in a
  // complete decision tree for the first turn.
  public String firstTurn(String card1, String card2, String dealerCard) {
    int handScore = parseCard(card1) + parseCard(card2);
    int dealerScore = parseCard(dealerCard);

    return (handScore > 20)
        ? largeHand(isBlackjack(card1, card2), dealerScore)
        : smallHand(handScore, dealerScore);
  }
}
