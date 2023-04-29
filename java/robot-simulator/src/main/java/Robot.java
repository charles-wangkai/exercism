public class Robot {
  static final int[] X_OFFSETS = {0, 1, 0, -1};
  static final int[] Y_OFFSETS = {1, 0, -1, 0};

  GridPosition pos;
  Orientation orientation;

  Robot(GridPosition pos, Orientation orientation) {
    this.pos = pos;
    this.orientation = orientation;
  }

  GridPosition getGridPosition() {
    return pos;
  }

  Orientation getOrientation() {
    return orientation;
  }

  void turnRight() {
    orientation = Orientation.values()[(orientation.ordinal() + 1) % Orientation.values().length];
  }

  void turnLeft() {
    orientation =
        Orientation.values()[Math.floorMod(orientation.ordinal() - 1, Orientation.values().length)];
  }

  void advance() {
    pos =
        new GridPosition(
            pos.x + X_OFFSETS[orientation.ordinal()], pos.y + Y_OFFSETS[orientation.ordinal()]);
  }

  void simulate(String instructions) {
    for (char instruction : instructions.toCharArray()) {
      if (instruction == 'R') {
        turnRight();
      } else if (instruction == 'L') {
        turnLeft();
      } else if (instruction == 'A') {
        advance();
      }
    }
  }
}
