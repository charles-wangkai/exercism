import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HandshakeCalculator {
	List<Signal> calculateHandshake(int number) {
		Signal[] signalValues = Signal.values();

		List<Signal> signals = new ArrayList<Signal>();
		for (int i = 0; i < signalValues.length; i++) {
			if ((number & 1) != 0) {
				signals.add(signalValues[i]);
			}

			number >>= 1;
		}
		if (number != 0) {
			Collections.reverse(signals);
		}
		return signals;
	}
}
