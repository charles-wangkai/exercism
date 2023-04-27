import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ConnectTest {

  @Test
  public void anEmptyBoardHasNoWinner() {

    // GIVEN
    var board =
        new String[] {". . . . .", " . . . . .", "  . . . . .", "   . . . . .", "    . . . . ."};
    Connect cut = new Connect(board);

    // WHEN
    var winner = cut.computeWinner();

    // THEN
    assertThat(winner).isEqualTo(Winner.NONE);
  }

  @Test
  public void xCanWinOnA1x1Board() {

    // GIVEN
    var board = new String[] {"X"};
    Connect cut = new Connect(board);

    // WHEN
    var winner = cut.computeWinner();

    // THEN
    assertThat(winner).isEqualTo(Winner.PLAYER_X);
  }

  @Test
  public void oCanWinOnA1x1Board() {

    // GIVEN
    var board = new String[] {"O"};
    Connect cut = new Connect(board);

    // WHEN
    var winner = cut.computeWinner();

    // THEN
    assertThat(winner).isEqualTo(Winner.PLAYER_O);
  }

  @Test
  public void onlyEdgesDoesNotMakeAWinner() {

    // GIVEN
    var board = new String[] {"O O O X", " X . . X", "  X . . X", "   X O O O"};
    Connect cut = new Connect(board);

    // WHEN
    var winner = cut.computeWinner();

    // THEN
    assertThat(winner).isEqualTo(Winner.NONE);
  }

  @Test
  public void illegalDiagonalDoesNotMakeAWinner() {

    // GIVEN
    var board = new String[] {"X O . .", " O X X X", "  O X O .", "   . O X .", "    X X O O"};
    Connect cut = new Connect(board);

    // WHEN
    var winner = cut.computeWinner();

    // THEN
    assertThat(winner).isEqualTo(Winner.NONE);
  }

  @Test
  public void nobodyWinsCrossingAdjacentAngles() {

    // GIVEN
    var board = new String[] {"X . . .", " . X O .", "  O . X O", "   . O . X", "    . . O ."};
    Connect cut = new Connect(board);

    // WHEN
    var winner = cut.computeWinner();

    // THEN
    assertThat(winner).isEqualTo(Winner.NONE);
  }

  @Test
  public void xWinsCrossingFromLeftToRight() {

    // GIVEN
    var board = new String[] {". O . .", " O X X X", "  O X O .", "   X X O X", "    . O X ."};
    Connect cut = new Connect(board);

    // WHEN
    var winner = cut.computeWinner();

    // THEN
    assertThat(winner).isEqualTo(Winner.PLAYER_X);
  }

  @Test
  public void oWinsCrossingFromTopToBottom() {

    // GIVEN
    var board = new String[] {". O . .", " O X X X", "  O O O .", "   X X O X", "    . O X ."};
    Connect cut = new Connect(board);

    // WHEN
    var winner = cut.computeWinner();

    // THEN
    assertThat(winner).isEqualTo(Winner.PLAYER_O);
  }

  @Test
  public void xWinsUsingConvolutedPath() {

    // GIVEN
    var board =
        new String[] {". X X . .", " X . X . X", "  . X . X .", "   . X X . .", "    O O O O O"};
    Connect cut = new Connect(board);

    // WHEN
    var winner = cut.computeWinner();

    // THEN
    assertThat(winner).isEqualTo(Winner.PLAYER_X);
  }

  @Test
  public void xWinsUsingASpiralPath() {

    // GIVEN
    var board =
        new String[] {
          "O X X X X X X X X",
          " O X O O O O O O O",
          "  O X O X X X X X O",
          "   O X O X O O O X O",
          "    O X O X X X O X O",
          "     O X O O O X O X O",
          "      O X X X X X O X O",
          "       O O O O O O O X O",
          "        X X X X X X X X O"
        };
    Connect cut = new Connect(board);

    // WHEN
    var winner = cut.computeWinner();

    // THEN
    assertThat(winner).isEqualTo(Winner.PLAYER_X);
  }
}
