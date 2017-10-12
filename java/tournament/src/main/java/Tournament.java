import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tournament {
	Map<String, Team> name2team = new HashMap<String, Team>();

	String printTable() {
		StringBuilder result = new StringBuilder();
		result.append("Team                           | MP |  W |  D |  L |  P");
		result.append("\n");

		List<Team> teams = new ArrayList<Team>(name2team.values());
		Collections.sort(teams,
				(team1, team2) -> (team1.getP() != team2.getP()) ? Integer.compare(team2.getP(), team1.getP())
						: team1.name.compareTo(team2.name));
		for (Team team : teams) {
			result.append(String.format("%-30s | %2d | %2d | %2d | %2d | %2d", team.name, team.getMP(), team.w, team.d,
					team.l, team.getP()));
			result.append("\n");
		}

		return result.toString();
	}

	void applyResults(String results) {
		for (String result : results.split("\n")) {
			String[] fields = result.split(";");

			String name1 = fields[0];
			if (!name2team.containsKey(name1)) {
				name2team.put(name1, new Team(name1));
			}
			Team team1 = name2team.get(name1);

			String name2 = fields[1];
			if (!name2team.containsKey(name2)) {
				name2team.put(name2, new Team(name2));
			}
			Team team2 = name2team.get(name2);

			String outcome = fields[2];
			if (outcome.equals("win")) {
				team1.w++;
				team2.l++;
			} else if (outcome.equals("draw")) {
				team1.d++;
				team2.d++;
			} else if (outcome.equals("loss")) {
				team1.l++;
				team2.w++;
			}
		}
	}
}

class Team {
	String name;
	int w;
	int d;
	int l;

	Team(String name) {
		this.name = name;
	}

	int getMP() {
		return w + d + l;
	}

	int getP() {
		return w * 3 + d;
	}
}