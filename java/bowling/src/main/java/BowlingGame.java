import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class BowlingGame {
	List<Integer> rolls = new ArrayList<Integer>();

	void roll(int pins) {
		rolls.add(pins);
	}

	int score() {
		List<Frame> frames = new ArrayList<Frame>();
		Iterator<Integer> iter = rolls.iterator();
		for (int i = 0; i < 10; i++) {
			frames.add(readFrame(iter));
		}

		FrameType frame9Type = frames.get(9).getFrameType();
		if (frame9Type == FrameType.SPARE) {
			int roll1 = readRoll(iter);

			frames.add(new Frame(roll1, 0));
		} else if (frame9Type == FrameType.STRIKE) {
			int roll1 = readRoll(iter);
			int roll2 = readRoll(iter);

			if (roll1 != 10 && roll1 + roll2 > 10) {
				warnForMorePin();
			}

			frames.add(new Frame(roll1, roll2));
		}

		if (iter.hasNext()) {
			warnForMoreRoll();
		}

		int result = 0;
		for (int i = 0; i < 9; i++) {
			Frame frame = frames.get(i);
			result += frame.getScore();

			if (frame.getFrameType() == FrameType.SPARE) {
				result += frames.get(i + 1).roll1;
			} else if (frame.getFrameType() == FrameType.STRIKE) {
				int nextRoll = frames.get(i + 1).roll1;
				int nextNextRoll;
				if (nextRoll == 10) {
					nextNextRoll = frames.get(i + 2).roll1;
				} else {
					nextNextRoll = frames.get(i + 1).roll2;
				}

				result += nextRoll + nextNextRoll;
			}
		}
		result += IntStream.range(9, frames.size()).map(i -> frames.get(i).getScore()).sum();
		return result;
	}

	int readRoll(Iterator<Integer> iter) {
		checkAndWarnForLessRoll(iter);
		int roll = iter.next();
		if (roll < 0) {
			throw new IllegalStateException("Negative roll is invalid");
		}
		if (roll > 10) {
			warnForMorePin();
		}
		return roll;
	}

	Frame readFrame(Iterator<Integer> iter) {
		int roll1 = readRoll(iter);
		if (roll1 == 10) {
			return new Frame(10, 0);
		}

		int roll2 = readRoll(iter);
		if (roll1 + roll2 > 10) {
			warnForMorePin();
		}

		return new Frame(roll1, roll2);
	}

	void warnForMorePin() {
		throw new IllegalStateException("Pin count exceeds pins on the lane");
	}

	void checkAndWarnForLessRoll(Iterator<Integer> iter) {
		if (!iter.hasNext()) {
			throw new IllegalStateException("Score cannot be taken until the end of the game");
		}
	}

	void warnForMoreRoll() {
		throw new IllegalStateException("Cannot roll after game is over");
	}
}

class Frame {
	int roll1;
	int roll2;

	Frame(int roll1, int roll2) {
		this.roll1 = roll1;
		this.roll2 = roll2;
	}

	int getScore() {
		return roll1 + roll2;
	}

	FrameType getFrameType() {
		if (roll1 == 10) {
			return FrameType.STRIKE;
		} else if (getScore() == 10) {
			return FrameType.SPARE;
		} else {
			return FrameType.OPEN;
		}
	}
}

enum FrameType {
	OPEN, SPARE, STRIKE
}