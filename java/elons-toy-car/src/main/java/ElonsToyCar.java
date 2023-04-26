public class ElonsToyCar {
  static final int MAX_DRIVE_COUNT = 100;

  int driveCount;

  public static ElonsToyCar buy() {
    return new ElonsToyCar();
  }

  public String distanceDisplay() {
    return String.format("Driven %s meters", driveCount * 20);
  }

  public String batteryDisplay() {
    return (driveCount == MAX_DRIVE_COUNT)
        ? "Battery empty"
        : String.format("Battery at %d%%", MAX_DRIVE_COUNT - driveCount);
  }

  public void drive() {
    if (driveCount != MAX_DRIVE_COUNT) {
      ++driveCount;
    }
  }
}
