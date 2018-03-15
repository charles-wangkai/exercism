import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GoCounting {
	static final int[] X_OFFSETS = { 0, 1, 0, -1 };
	static final int[] Y_OFFSETS = { -1, 0, 1, 0 };

	String[] board;

	GoCounting(String s) {
		board = s.split("\n");
	}

	SpreadResult spread(int x, int y) {
		int row = board.length;
		int col = board[0].length();

		if (!(x >= 0 && x < col && y >= 0 && y < row)) {
			throw new IllegalArgumentException("Invalid coordinate");
		}

		SpreadResult spreadResult = new SpreadResult();

		if (board[y].charAt(x) != ' ') {
			return spreadResult;
		}

		search(new Point(x, y), spreadResult);

		return spreadResult;
	}

	void search(Point point, SpreadResult spreadResult) {
		int row = board.length;
		int col = board[0].length();

		spreadResult.territory.add(point);

		for (int i = 0; i < X_OFFSETS.length; i++) {
			int adjX = point.x + X_OFFSETS[i];
			int adjY = point.y + Y_OFFSETS[i];

			if (adjX >= 0 && adjX < col && adjY >= 0 && adjY < row) {
				if (board[adjY].charAt(adjX) == ' ') {
					Point adjPoint = new Point(adjX, adjY);
					if (!spreadResult.territory.contains(adjPoint)) {
						search(adjPoint, spreadResult);
					}
				} else {
					spreadResult.stones.add(Player.fromCharater(board[adjY].charAt(adjX)));
				}
			}
		}
	}

	Player getTerritoryOwner(int x, int y) {
		Set<Player> stones = spread(x, y).stones;

		if (stones.size() == 1) {
			return stones.iterator().next();
		} else {
			return Player.NONE;
		}
	}

	Set<Point> getTerritory(int x, int y) {
		return spread(x, y).territory;
	}

	Map<String, Set<Point>> getTerritories() {
		int row = board.length;
		int col = board[0].length();

		Map<String, Set<Point>> territories = new HashMap<String, Set<Point>>();
		for (Player owner : Player.values()) {
			Set<Point> territory = new HashSet<Point>();
			for (int x = 0; x < col; x++) {
				for (int y = 0; y < row; y++) {
					if (board[y].charAt(x) == ' ' && getTerritoryOwner(x, y) == owner) {
						territory.add(new Point(x, y));
					}
				}
			}

			territories.put(owner.toString(), territory);
		}
		return territories;
	}
}

class SpreadResult {
	Set<Point> territory = new HashSet<Point>();
	Set<Player> stones = new HashSet<Player>();
}