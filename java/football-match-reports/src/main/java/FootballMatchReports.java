public class FootballMatchReports {
  public static String onField(int shirtNum) {
    if (shirtNum == 1) {
      return "goalie";
    }
    if (shirtNum == 2) {
      return "left back";
    }
    if (shirtNum == 3 || shirtNum == 4) {
      return "center back";
    }
    if (shirtNum == 5) {
      return "right back";
    }
    if (shirtNum >= 6 && shirtNum <= 8) {
      return "midfielder";
    }
    if (shirtNum == 9) {
      return "left wing";
    }
    if (shirtNum == 10) {
      return "striker";
    }
    if (shirtNum == 11) {
      return "right wing";
    }

    throw new IllegalArgumentException();
  }
}
