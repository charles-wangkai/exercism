import java.util.ArrayList;
import java.util.List;

class HandshakeCalculator {
	static final Operation[] OPERATIONS = { signals -> signals.add(Signal.WINK),
			signals -> signals.add(Signal.DOUBLE_BLINK), signals -> signals.add(Signal.CLOSE_YOUR_EYES),
			signals -> signals.add(Signal.JUMP), signals -> {
				for (int i = 0, j = signals.size() - 1; i < j; i++, j--) {
					Signal temp = signals.get(i);
					signals.set(i, signals.get(j));
					signals.set(j, temp);
				}
			} };

	List<Signal> calculateHandshake(int number) {
		List<Signal> signals = new ArrayList<Signal>();
		for (Operation operation : OPERATIONS) {
			if ((number & 1) != 0) {
				operation.operate(signals);
			}

			number >>= 1;
		}
		return signals;
	}

}

@FunctionalInterface
interface Operation {
	void operate(List<Signal> signals);
}