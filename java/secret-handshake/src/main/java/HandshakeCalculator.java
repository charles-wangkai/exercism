import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class HandshakeCalculator {
  List<Signal> calculateHandshake(int number) {
    List<Signal> result =
        IntStream.range(0, Signal.values().length)
            .filter(i -> ((number >> i) & 1) == 1)
            .mapToObj(i -> Signal.values()[i])
            .collect(Collectors.toList());
    if (((number >> 4) & 1) == 1) {
      Collections.reverse(result);
    }

    return result;
  }
}
