class NeedForSpeed {
  int speed;
  int batteryDrain;
  int driveCount;

  public NeedForSpeed(int speed, int batteryDrain) {
    this.speed = speed;
    this.batteryDrain = batteryDrain;
  }

  public boolean batteryDrained() {
    return batteryDrain * (driveCount + 1) > 100;
  }

  public int distanceDriven() {
    return speed * driveCount;
  }

  public void drive() {
    if (!batteryDrained()) {
      ++driveCount;
    }
  }

  public static NeedForSpeed nitro() {
    return new NeedForSpeed(50, 4);
  }
}

class RaceTrack {
  int distance;

  public RaceTrack(int distance) {
    this.distance = distance;
  }

  public boolean canFinishRace(NeedForSpeed car) {
    return 100 / car.batteryDrain * car.speed >= distance;
  }
}
