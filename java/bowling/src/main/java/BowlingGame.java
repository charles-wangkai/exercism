import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class BowlingGame {
  List<Integer> rolls = new ArrayList<>();

  void roll(int pins) {
    rolls.add(pins);
    buildFrames();
  }

  List<Frame> buildFrames() {
    List<Frame> frames = new ArrayList<>();
    Iterator<Integer> iter = rolls.iterator();
    for (int i = 0; i < 10; ++i) {
      Frame frame = readFrame(iter);
      if (frame == null) {
        return null;
      }

      frames.add(frame);
    }

    FrameType frame9Type = frames.get(9).getFrameType();
    if (frame9Type == FrameType.SPARE) {
      Integer roll1 = readRoll(iter);
      if (roll1 == null) {
        return null;
      }

      frames.add(new Frame(roll1, 0));
    } else if (frame9Type == FrameType.STRIKE) {
      Integer roll1 = readRoll(iter);
      if (roll1 == null) {
        return null;
      }

      Integer roll2 = readRoll(iter);
      if (roll2 == null) {
        return null;
      }

      if (roll1 != 10 && roll1 + roll2 > 10) {
        warnForMorePin();
      }

      frames.add(new Frame(roll1, roll2));
    }

    if (iter.hasNext()) {
      warnForMoreRoll();
    }

    return frames;
  }

  int score() {
    List<Frame> frames = buildFrames();
    if (frames == null) {
      throw new IllegalStateException("Score cannot be taken until the end of the game");
    }

    int result = 0;
    for (int i = 0; i < 9; ++i) {
      Frame frame = frames.get(i);
      result += frame.getScore();

      if (frame.getFrameType() == FrameType.SPARE) {
        result += frames.get(i + 1).roll1();
      } else if (frame.getFrameType() == FrameType.STRIKE) {
        int nextRoll = frames.get(i + 1).roll1();
        int nextNextRoll = (nextRoll == 10) ? frames.get(i + 2).roll1() : frames.get(i + 1).roll2();

        result += nextRoll + nextNextRoll;
      }
    }
    result += IntStream.range(9, frames.size()).map(i -> frames.get(i).getScore()).sum();

    return result;
  }

  Integer readRoll(Iterator<Integer> iter) {
    if (!iter.hasNext()) {
      return null;
    }

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
    Integer roll1 = readRoll(iter);
    if (roll1 == null) {
      return null;
    }
    if (roll1 == 10) {
      return new Frame(10, 0);
    }

    Integer roll2 = readRoll(iter);
    if (roll2 == null) {
      return null;
    }
    if (roll1 + roll2 > 10) {
      warnForMorePin();
    }

    return new Frame(roll1, roll2);
  }

  void warnForMorePin() {
    throw new IllegalStateException("Pin count exceeds pins on the lane");
  }

  void warnForMoreRoll() {
    throw new IllegalStateException("Cannot roll after game is over");
  }
}

record Frame(int roll1, int roll2) {
  int getScore() {
    return roll1 + roll2;
  }

  FrameType getFrameType() {
    if (roll1 == 10) {
      return FrameType.STRIKE;
    }
    if (getScore() == 10) {
      return FrameType.SPARE;
    }

    return FrameType.OPEN;
  }
}

enum FrameType {
  OPEN,
  SPARE,
  STRIKE
}
