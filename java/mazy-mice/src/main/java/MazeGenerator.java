// https://en.wikipedia.org/wiki/Maze_generation_algorithm#Randomized_depth-first_search

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MazeGenerator {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public char[][] generatePerfectMaze(int rows, int columns) {
    return generate(rows, columns, new Random());
  }

  public char[][] generatePerfectMaze(int rows, int columns, int seed) {
    return generate(rows, columns, new Random(seed));
  }

  char[][] generate(int rows, int columns, Random random) {
    if (rows < 5 || rows > 100 || columns < 5 || columns > 100) {
      throw new IllegalArgumentException();
    }

    boolean[][][] walls = new boolean[rows][columns][4];
    for (int r = 0; r < rows; ++r) {
      for (int c = 0; c < columns; ++c) {
        Arrays.fill(walls[r][c], true);
      }
    }

    search(
        walls, new boolean[rows][columns], random.nextInt(rows), random.nextInt(columns), random);

    return display(walls, random.nextInt(rows), random.nextInt(rows));
  }

  void search(boolean[][][] walls, boolean[][] visited, int r, int c, Random random) {
    int rows = walls.length;
    int columns = walls[0].length;

    visited[r][c] = true;

    List<Integer> directions =
        IntStream.range(0, R_OFFSETS.length).boxed().collect(Collectors.toList());
    Collections.shuffle(directions, random);
    for (int direction : directions) {
      int adjR = r + R_OFFSETS[direction];
      int adjC = c + C_OFFSETS[direction];
      if (adjR >= 0 && adjR < rows && adjC >= 0 && adjC < columns && !visited[adjR][adjC]) {
        walls[r][c][direction] = false;
        walls[adjR][adjC][(direction + 2) % R_OFFSETS.length] = false;

        search(walls, visited, adjR, adjC, random);
      }
    }
  }

  char[][] display(boolean[][][] walls, int entranceRow, int exitRow) {
    int rows = walls.length;
    int columns = walls[0].length;

    char[][] result = new char[rows * 2 + 1][columns * 2 + 1];
    for (int r = 0; r < rows; ++r) {
      for (int c = 0; c < columns; ++c) {
        result[r * 2 + 1][c * 2 + 1] = ' ';
      }
    }
    for (int c = 0; c < columns; ++c) {
      result[0][c * 2 + 1] = '─';
      result[rows * 2][c * 2 + 1] = '─';

      for (int r = 1; r < rows; ++r) {
        result[r * 2][c * 2 + 1] = walls[r][c][0] ? '─' : ' ';
      }
    }
    for (int r = 0; r < rows; ++r) {
      result[r * 2 + 1][0] = '│';
      result[r * 2 + 1][columns * 2] = '│';

      for (int c = 1; c < columns; ++c) {
        result[r * 2 + 1][c * 2] = walls[r][c][3] ? '│' : ' ';
      }
    }
    for (int r = 0; r <= rows; ++r) {
      for (int c = 0; c <= columns; ++c) {
        boolean up = r != 0 && result[r * 2 - 1][c * 2] == '│';
        boolean right = c != columns && result[r * 2][c * 2 + 1] == '─';
        boolean down = r != rows && result[r * 2 + 1][c * 2] == '│';
        boolean left = c != 0 && result[r * 2][c * 2 - 1] == '─';
        if (up && right && down && left) {
          result[r * 2][c * 2] = '┼';
        } else if (right && down && left) {
          result[r * 2][c * 2] = '┬';
        } else if (up && down && left) {
          result[r * 2][c * 2] = '┤';
        } else if (up && right && left) {
          result[r * 2][c * 2] = '┴';
        } else if (up && right && down) {
          result[r * 2][c * 2] = '├';
        } else if (up && right) {
          result[r * 2][c * 2] = '└';
        } else if (right && down) {
          result[r * 2][c * 2] = '┌';
        } else if (down && left) {
          result[r * 2][c * 2] = '┐';
        } else if (left && up) {
          result[r * 2][c * 2] = '┘';
        } else if (up && down) {
          result[r * 2][c * 2] = '│';
        } else if (left && right) {
          result[r * 2][c * 2] = '─';
        } else {
          result[r * 2][c * 2] = ' ';
        }
      }
    }
    result[entranceRow * 2 + 1][0] = '⇨';
    result[exitRow * 2 + 1][columns * 2] = '⇨';

    return result;
  }
}
