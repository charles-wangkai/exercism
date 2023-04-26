public class CarsAssemble {
  public double productionRatePerHour(int speed) {
    return 221 * speed * getSuccessRate(speed);
  }

  public int workingItemsPerMinute(int speed) {
    return (int) Math.floor(productionRatePerHour(speed) / 60);
  }

  double getSuccessRate(int speed) {
    if (speed == 0) {
      return 0;
    }
    if (speed <= 4) {
      return 1;
    }
    if (speed <= 8) {
      return 0.9;
    }
    if (speed == 9) {
      return 0.8;
    }

    return 0.77;
  }
}
