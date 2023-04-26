import java.util.List;

public class TestTrack {
  public static void race(RemoteControlCar car) {
    car.drive();
  }

  public static List<ProductionRemoteControlCar> getRankedCars(
      List<ProductionRemoteControlCar> productionCars) {
    return productionCars.stream().sorted().toList();
  }
}
