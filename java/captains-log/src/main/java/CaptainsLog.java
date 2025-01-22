import java.util.Random;

class CaptainsLog {

  private static final char[] PLANET_CLASSES =
      new char[] {'D', 'H', 'J', 'K', 'L', 'M', 'N', 'R', 'T', 'Y'};

  private Random random;

  CaptainsLog(Random random) {
    this.random = random;
  }

  char randomPlanetClass() {
    return PLANET_CLASSES[random.nextInt(PLANET_CLASSES.length)];
  }

  String randomShipRegistryNumber() {
    return "NCC-%d".formatted(1000 + random.nextInt(9000));
  }

  double randomStardate() {
    return 41000 + random.nextDouble() * 1000;
  }
}
