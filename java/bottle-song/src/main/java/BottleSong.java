import java.util.stream.Collectors;
import java.util.stream.IntStream;

class BottleSong {
  static final String[] VALUE_NAMES = {
    "No", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"
  };

  String recite(int startBottles, int takeDown) {
    return IntStream.range(0, takeDown)
        .mapToObj(
            i ->
                """
                %s green bottle%s hanging on the wall,
                %s green bottle%s hanging on the wall,
                And if one green bottle should accidentally fall,
                There'll be %s green bottle%s hanging on the wall.
                """
                    .formatted(
                        VALUE_NAMES[startBottles - i],
                        (startBottles - i == 1) ? "" : "s",
                        VALUE_NAMES[startBottles - i],
                        (startBottles - i == 1) ? "" : "s",
                        VALUE_NAMES[startBottles - i - 1].toLowerCase(),
                        (startBottles - i - 1 == 1) ? "" : "s"))
        .collect(Collectors.joining("\n"));
  }
}