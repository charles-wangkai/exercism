import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RectangleCounterTest {

  private RectangleCounter rectangleCounter;

  @BeforeEach
  public void setUp() {
    rectangleCounter = new RectangleCounter();
  }

  @Test
  public void testInputWithNoRowsContainsNoRectangles() {
    String[] inputGrid = new String[] {};

    assertThat(rectangleCounter.countRectangles(inputGrid)).isEqualTo(0);
  }

  @Test
  public void testInputWithNoColumnsContainsNoRectangles() {
    String[] inputGrid = new String[] {""};

    assertThat(rectangleCounter.countRectangles(inputGrid)).isEqualTo(0);
  }

  @Test
  public void testNonTrivialInputWithNoRectangles() {
    String[] inputGrid = new String[] {" "};

    assertThat(rectangleCounter.countRectangles(inputGrid)).isEqualTo(0);
  }

  @Test
  public void testInputWithOneRectangle() {
    String[] inputGrid = new String[] {"+-+", "| |", "+-+"};

    assertThat(rectangleCounter.countRectangles(inputGrid)).isEqualTo(1);
  }

  @Test
  public void testInputWithTwoRectanglesWithoutSharedEdges() {
    String[] inputGrid = new String[] {"  +-+", "  | |", "+-+-+", "| |  ", "+-+  "};

    assertThat(rectangleCounter.countRectangles(inputGrid)).isEqualTo(2);
  }

  @Test
  public void testInputWithFiveRectanglesWithSharedEdges() {
    String[] inputGrid = new String[] {"  +-+", "  | |", "+-+-+", "| | |", "+-+-+"};

    assertThat(rectangleCounter.countRectangles(inputGrid)).isEqualTo(5);
  }

  @Test
  public void testThatRectangleOfHeightOneIsCounted() {
    String[] inputGrid = new String[] {"+--+", "+--+"};

    assertThat(rectangleCounter.countRectangles(inputGrid)).isEqualTo(1);
  }

  @Test
  public void testThatRectangleOfWidthOneIsCounted() {
    String[] inputGrid = new String[] {"++", "||", "++"};

    assertThat(rectangleCounter.countRectangles(inputGrid)).isEqualTo(1);
  }

  @Test
  public void testThatOneByOneSquareIsCounted() {
    String[] inputGrid = new String[] {"++", "++"};

    assertThat(rectangleCounter.countRectangles(inputGrid)).isEqualTo(1);
  }

  @Test
  public void testThatIncompleteRectanglesAreNotCounted() {
    String[] inputGrid = new String[] {"  +-+", "    |", "+-+-+", "| | -", "+-+-+"};

    assertThat(rectangleCounter.countRectangles(inputGrid)).isEqualTo(1);
  }

  @Test
  public void testThatRectanglesOfDifferentSizesAreAllCounted() {
    String[] inputGrid =
        new String[] {
          "+------+----+", "|      |    |", "+---+--+    |", "|   |       |", "+---+-------+"
        };

    assertThat(rectangleCounter.countRectangles(inputGrid)).isEqualTo(3);
  }

  @Test
  public void testThatIntersectionsWithoutCornerCharacterDoNotCountAsRectangleCorners() {
    String[] inputGrid =
        new String[] {
          "+------+----+", "|      |    |", "+------+    |", "|   |       |", "+---+-------+"
        };

    assertThat(rectangleCounter.countRectangles(inputGrid)).isEqualTo(2);
  }

  @Test
  public void testLargeInputWithManyRectangles() {
    String[] inputGrid =
        new String[] {
          "+---+--+----+",
          "|   +--+----+",
          "+---+--+    |",
          "|   +--+----+",
          "+---+--+--+-+",
          "+---+--+--+-+",
          "+------+  | |",
          "          +-+"
        };

    assertThat(rectangleCounter.countRectangles(inputGrid)).isEqualTo(60);
  }

  @Test
  public void testRectanglesMustHaveFourSides() {
    String[] inputGrid =
        new String[] {"+-+ +-+", "| | | |", "+-+-+-+", "  | |  ", "+-+-+-+", "| | | |", "+-+ +-+"};

    assertThat(rectangleCounter.countRectangles(inputGrid)).isEqualTo(5);
  }
}
