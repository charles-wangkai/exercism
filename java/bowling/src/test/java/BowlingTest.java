import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BowlingTest {
	private BowlingGame game;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setup() {
		game = new BowlingGame();
	}

	private void playGame(int[] rolls) {
		for (int pins : rolls) {
			game.roll(pins);
		}
	}

	@Test
	public void shouldBeAbleToScoreAGameWithAllZeros() {
		int[] rolls = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		playGame(rolls);
		assertEquals(0, game.score());
	}

	@Test
	public void shouldBeAbleToScoreAGameWithNoStrikesOrSpares() {
		int[] rolls = { 3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6 };

		playGame(rolls);
		assertEquals(90, game.score());
	}

	@Test
	public void aSpareFollowedByZerosIsWorthTenPoints() {
		int[] rolls = { 6, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		playGame(rolls);
		assertEquals(10, game.score());
	}

	@Test
	public void pointsScoredInTheRollAfterASpareAreCountedTwice() {
		int[] rolls = { 6, 4, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		playGame(rolls);
		assertEquals(16, game.score());
	}

	@Test
	public void consecutiveSparesEachGetAOneRollBonus() {
		int[] rolls = { 5, 5, 3, 7, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		playGame(rolls);
		assertEquals(31, game.score());
	}

	@Test
	public void aSpareInTheLastFrameGetsAOneRollBonusThatIsCountedOnce() {
		int[] rolls = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 3, 7 };

		playGame(rolls);
		assertEquals(17, game.score());
	}

	@Test
	public void aStrikeEarnsTenPointsInFrameWithASingleRoll() {
		int[] rolls = { 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		playGame(rolls);
		assertEquals(10, game.score());
	}

	@Test
	public void pointsScoredInTheTwoRollsAfterAStrikeAreCountedTwiceAsABonus() {
		int[] rolls = { 10, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		playGame(rolls);
		assertEquals(26, game.score());
	}

	@Test
	public void consecutiveStrikesEachGetTheTwoRollBonus() {
		int[] rolls = { 10, 10, 10, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		playGame(rolls);
		assertEquals(81, game.score());
	}

	@Test
	public void aStrikeInTheLastFrameGetsATwoRollBonusThatIsCountedOnce() {
		int[] rolls = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 7, 1 };

		playGame(rolls);
		assertEquals(18, game.score());
	}

	@Test
	public void rollingASpareWithTheTwoRollBonusDoesNotGetABonusRoll() {
		int[] rolls = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 7, 3 };

		playGame(rolls);
		assertEquals(20, game.score());
	}

	@Test
	public void strikesWithTheTwoRollBonusDoNotGetBonusRolls() {
		int[] rolls = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 10 };

		playGame(rolls);
		assertEquals(30, game.score());
	}

	@Test
	public void aStrikeWithTheOneRollBonusAfterASpareInTheLastFrameDoesNotGetABonus() {
		int[] rolls = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 3, 10 };

		playGame(rolls);
		assertEquals(20, game.score());
	}

	@Test
	public void allStrikesIsAPerfectGame() {
		int[] rolls = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };

		playGame(rolls);
		assertEquals(300, game.score());
	}

	@Test
	public void rollsCanNotScoreNegativePoints() throws IllegalStateException {
		int[] rolls = { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		playGame(rolls);

		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("Negative roll is invalid");

		game.score();
	}

	@Test
	public void aRollCanNotScoreMoreThan10Points() throws IllegalStateException {
		int[] rolls = { 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		playGame(rolls);

		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("Pin count exceeds pins on the lane");

		game.score();
	}

	@Test
	public void twoRollsInAFrameCanNotScoreMoreThan10Points() throws IllegalStateException {
		int[] rolls = { 5, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		playGame(rolls);

		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("Pin count exceeds pins on the lane");

		game.score();
	}

	@Test
	public void twoBonusRollsAfterAStrikeInTheLastFrameCanNotScoreMoreThan10Points() throws IllegalStateException {
		int[] rolls = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 5, 6 };

		playGame(rolls);

		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("Pin count exceeds pins on the lane");

		game.score();
	}

	@Test
	public void twoBonusRollsAfterAStrikeInTheLastFrameCanScoreMoreThan10PointsIfOneIsAStrike()
			throws IllegalStateException {
		int[] rolls = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 6 };

		playGame(rolls);

		assertEquals(26, game.score());
	}

	@Test
	public void anUnstartedGameCanNotBeScored() throws IllegalStateException {
		int[] rolls = new int[0];

		playGame(rolls);

		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("Score cannot be taken until the end of the game");

		game.score();
	}

	@Test
	public void anIncompleteGameCanNotBeScored() throws IllegalStateException {
		int[] rolls = { 0, 0 };

		playGame(rolls);

		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("Score cannot be taken until the end of the game");

		game.score();
	}

	@Test
	public void aGameWithMoreThanTenFramesCanNotBeScored() throws IllegalStateException {
		int[] rolls = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		playGame(rolls);

		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("Cannot roll after game is over");

		game.score();
	}

	@Test
	public void bonusRollsForAStrikeInTheLastFrameMustBeRolledBeforeScoreCanBeCalculated()
			throws IllegalStateException {
		int[] rolls = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10 };

		playGame(rolls);

		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("Score cannot be taken until the end of the game");

		game.score();
	}

	@Test
	public void bothBonusRollsForAStrikeInTheLastFrameMustBeRolledBeforeScoreCanBeCalculated()
			throws IllegalStateException {
		int[] rolls = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10 };

		playGame(rolls);

		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("Score cannot be taken until the end of the game");

		game.score();
	}

	@Test
	public void bonusRollForASpareInTheLastFrameMustBeRolledBeforeScoreCanBeCalculated() throws IllegalStateException {
		int[] rolls = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 3 };

		playGame(rolls);

		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("Score cannot be taken until the end of the game");

		game.score();
	}

}