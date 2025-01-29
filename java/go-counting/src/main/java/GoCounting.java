import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GoCounting {
  static final int[] X_OFFSETS = {0, 1, 0, -1};
  static final int[] Y_OFFSETS = {-1, 0, 1, 0};

  String[] board;

  GoCounting(String s) {
    board = s.split("\n");
  }

  Outcome spread(int x, int y) {
    int row = board.length;
    int col = board[0].length();

    if (!(x >= 0 && x < col && y >= 0 && y < row)) {
      throw new IllegalArgumentException("Invalid coordinate");
    }

    Outcome outcome = new Outcome(new HashSet<>(), new HashSet<>());

    if (board[y].charAt(x) != ' ') {
      return outcome;
    }

    search(outcome.territory(), outcome.stones(), new Point(x, y));

    return outcome;
  }

  void search(Set<Point> territory, Set<Player> stones, Point point) {
    int row = board.length;
    int col = board[0].length();

    territory.add(point);

    for (int i = 0; i < X_OFFSETS.length; ++i) {
      int adjX = point.x + X_OFFSETS[i];
      int adjY = point.y + Y_OFFSETS[i];
      if (adjX >= 0 && adjX < col && adjY >= 0 && adjY < row) {
        if (board[adjY].charAt(adjX) == ' ') {
          Point adjPoint = new Point(adjX, adjY);
          if (!territory.contains(adjPoint)) {
            search(territory, stones, adjPoint);
          }
        } else {
          stones.add(toPlayer(board[adjY].charAt(adjX)));
        }
      }
    }
  }

  Player getTerritoryOwner(int x, int y) {
    Set<Player> stones = spread(x, y).stones();

    return (stones.size() == 1) ? stones.iterator().next() : Player.NONE;
  }

  Set<Point> getTerritory(int x, int y) {
    return spread(x, y).territory();
  }

  Map<Player, Set<Point>> getTerritories() {
    int row = board.length;
    int col = board[0].length();

    Map<Player, Set<Point>> territories = new HashMap<>();
    for (Player owner : Player.values()) {
      Set<Point> territory = new HashSet<>();
      for (int x = 0; x < col; ++x) {
        for (int y = 0; y < row; ++y) {
          if (board[y].charAt(x) == ' ' && getTerritoryOwner(x, y) == owner) {
            territory.add(new Point(x, y));
          }
        }
      }

      territories.put(owner, territory);
    }

    return territories;
  }

  Player toPlayer(char c) {
    if (c == 'B') {
      return Player.BLACK;
    }
    if (c == 'W') {
      return Player.WHITE;
    }

    return Player.NONE;
  }
}

record Outcome(Set<Point> territory, Set<Player> stones) {}
