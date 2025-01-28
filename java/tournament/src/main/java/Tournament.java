import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Tournament {
  Map<String, Team> nameToTeam = new HashMap<>();

  String printTable() {
    return "Team                           | MP |  W |  D |  L |  P\n%s"
        .formatted(
            nameToTeam.values().stream()
                .sorted(
                    Comparator.comparing(Team::getPoint)
                        .reversed()
                        .thenComparing(team -> team.name))
                .map(
                    team ->
                        "%-30s | %2d | %2d | %2d | %2d | %2d\n"
                            .formatted(
                                team.name,
                                team.winCount + team.drawCount + team.lossCount,
                                team.winCount,
                                team.drawCount,
                                team.lossCount,
                                team.getPoint()))
                .collect(Collectors.joining()));
  }

  void applyResults(String results) {
    for (String result : results.split("\n")) {
      String[] parts = result.split(";");

      String name1 = parts[0];
      nameToTeam.putIfAbsent(name1, new Team(name1));
      Team team1 = nameToTeam.get(name1);

      String name2 = parts[1];
      nameToTeam.putIfAbsent(name2, new Team(name2));
      Team team2 = nameToTeam.get(name2);

      String outcome = parts[2];
      if (outcome.equals("win")) {
        ++team1.winCount;
        ++team2.lossCount;
      } else if (outcome.equals("draw")) {
        ++team1.drawCount;
        ++team2.drawCount;
      } else if (outcome.equals("loss")) {
        ++team1.lossCount;
        ++team2.winCount;
      }
    }
  }
}

class Team {
  String name;
  int winCount;
  int drawCount;
  int lossCount;

  Team(String name) {
    this.name = name;
  }

  int getPoint() {
    return winCount * 3 + drawCount;
  }
}
