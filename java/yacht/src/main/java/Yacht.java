import java.util.Arrays;
import java.util.stream.IntStream;

class Yacht {
  int[] dice;
  YachtCategory yachtCategory;

  Yacht(int[] dice, YachtCategory yachtCategory) {
    this.dice = Arrays.stream(dice).sorted().toArray();
    this.yachtCategory = yachtCategory;
  }

  int score() {
    if (yachtCategory == YachtCategory.ONES) {
      return Arrays.stream(dice).filter(x -> x == 1).sum();
    }
    if (yachtCategory == YachtCategory.TWOS) {
      return Arrays.stream(dice).filter(x -> x == 2).sum();
    }
    if (yachtCategory == YachtCategory.THREES) {
      return Arrays.stream(dice).filter(x -> x == 3).sum();
    }
    if (yachtCategory == YachtCategory.FOURS) {
      return Arrays.stream(dice).filter(x -> x == 4).sum();
    }
    if (yachtCategory == YachtCategory.FIVES) {
      return Arrays.stream(dice).filter(x -> x == 5).sum();
    }
    if (yachtCategory == YachtCategory.SIXES) {
      return Arrays.stream(dice).filter(x -> x == 6).sum();
    }
    if (yachtCategory == YachtCategory.FULL_HOUSE && isFullHouse()) {
      return Arrays.stream(dice).sum();
    }
    if (yachtCategory == YachtCategory.FOUR_OF_A_KIND && isFourOfAKind()) {
      return dice[1] * 4;
    }
    if (yachtCategory == YachtCategory.LITTLE_STRAIGHT && isLittleStraight()) {
      return 30;
    }
    if (yachtCategory == YachtCategory.BIG_STRAIGHT && isBigStraight()) {
      return 30;
    }
    if (yachtCategory == YachtCategory.CHOICE) {
      return Arrays.stream(dice).sum();
    }
    if (yachtCategory == YachtCategory.YACHT && isYacht()) {
      return 50;
    }

    return 0;
  }

  boolean isFullHouse() {
    return dice[0] == dice[1]
        && dice[3] == dice[4]
        && dice[0] != dice[4]
        && (dice[2] == dice[1] || dice[2] == dice[3]);
  }

  boolean isFourOfAKind() {
    return dice[0] == dice[3] || dice[1] == dice[4];
  }

  boolean isLittleStraight() {
    return IntStream.range(0, 5).allMatch(i -> dice[i] == i + 1);
  }

  boolean isBigStraight() {
    return IntStream.range(0, 5).allMatch(i -> dice[i] == i + 2);
  }

  boolean isYacht() {
    return dice[0] == dice[4];
  }
}
