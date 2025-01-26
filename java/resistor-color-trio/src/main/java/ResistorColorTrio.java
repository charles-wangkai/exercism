import static java.util.Map.entry;

import java.util.Map;

class ResistorColorTrio {
  static final MetricPrefix[] METRIC_PREFIXS = {
    new MetricPrefix(1_000_000_000, "giga"),
    new MetricPrefix(1_000_000, "mega"),
    new MetricPrefix(1_000, "kilo")
  };

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

  String label(String[] colors) {
    long value =
        Long.parseLong(
            "%d%s"
                .formatted(
                    COLOR_TO_DIGIT.get(colors[0]) * 10 + COLOR_TO_DIGIT.get(colors[1]),
                    "0".repeat(COLOR_TO_DIGIT.get(colors[2]))));

    for (MetricPrefix metricPrefix : METRIC_PREFIXS) {
      if (value != 0 && value % metricPrefix.divisor() == 0) {
        return "%d %sohms".formatted(value / metricPrefix.divisor(), metricPrefix.prefix());
      }
    }

    return "%d ohms".formatted(value);
  }
}

record MetricPrefix(int divisor, String prefix) {}
