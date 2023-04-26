import static java.util.Map.entry;

import java.util.Map;

class ResistorColorDuo {
  static final Map<String, Integer> COLOR_TO_DIGIT =
      Map.ofEntries(
          entry("black", 0),
          entry("brown", 1),
          entry("red", 2),
          entry("orange", 3),
          entry("yellow", 4),
          entry("green", 5),
          entry("blue", 6),
          entry("violet", 7),
          entry("grey", 8),
          entry("white", 9));

  int value(String[] colors) {
    return COLOR_TO_DIGIT.get(colors[0]) * 10 + COLOR_TO_DIGIT.get(colors[1]);
  }
}
