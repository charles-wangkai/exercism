import java.util.Arrays;
import java.util.List;

public class KindergartenGarden {
  static final String[] DEFAULT_STUDENTS = {
    "Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph",
    "Kincaid", "Larry"
  };

  Plant[][] garden;
  List<String> students;

  KindergartenGarden(String plants) {
    this(plants, DEFAULT_STUDENTS);
  }

  KindergartenGarden(String plants, String[] studentArray) {
    String[] rows = plants.split("\n");
    garden = new Plant[rows.length][];
    for (int i = 0; i < garden.length; ++i) {
      garden[i] = new Plant[rows[i].length()];
      for (int j = 0; j < garden[i].length; ++j) {
        garden[i][j] = Plant.getPlant(rows[i].charAt(j));
      }
    }

    students = Arrays.stream(studentArray).sorted().toList();
  }

  List<Plant> getPlantsOfStudent(String student) {
    int index = students.indexOf(student);

    return List.of(
        garden[0][index * 2],
        garden[0][index * 2 + 1],
        garden[1][index * 2],
        garden[1][index * 2 + 1]);
  }
}
