class ProductionRemoteControlCar
    implements RemoteControlCar, Comparable<ProductionRemoteControlCar> {
  int distanceTravelled;
  int numberOfVictories;

  @Override
  public void drive() {
    distanceTravelled += 10;
  }

  @Override
  public int getDistanceTravelled() {
    return distanceTravelled;
  }

  public int getNumberOfVictories() {
    return numberOfVictories;
  }

  public void setNumberOfVictories(int numberOfVictories) {
    this.numberOfVictories = numberOfVictories;
  }

  @Override
  public int compareTo(ProductionRemoteControlCar other) {
    return -Integer.compare(numberOfVictories, other.numberOfVictories);
  }
}
