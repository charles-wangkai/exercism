public class Lasagna {
  public int expectedMinutesInOven() {
    return 40;
  }

  public int remainingMinutesInOven(int actualMinutesInOven) {
    return expectedMinutesInOven() - actualMinutesInOven;
  }

  public int preparationTimeInMinutes(int numberOfLayers) {
    return 2 * numberOfLayers;
  }

  public int totalTimeInMinutes(int numberOfLayers, int actualMinutesInOven) {
    return preparationTimeInMinutes(numberOfLayers) + actualMinutesInOven;
  }
}
