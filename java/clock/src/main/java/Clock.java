public class Clock {
  static final int MINUTE_IN_DAY = 24 * 60;

  int totalMinute;

  Clock(int hour, int minute) {
    totalMinute = Math.floorMod(hour * 60 + minute, MINUTE_IN_DAY);
  }

  void add(int minutesToAdd) {
    totalMinute = Math.floorMod(totalMinute + minutesToAdd, MINUTE_IN_DAY);
  }

  @Override
  public String toString() {
    return String.format("%02d:%02d", totalMinute / 60, totalMinute % 60);
  }

  @Override
  public int hashCode() {
    return totalMinute;
  }

  @Override
  public boolean equals(Object obj) {
    Clock other = (Clock) obj;

    return totalMinute == other.totalMinute;
  }
}
