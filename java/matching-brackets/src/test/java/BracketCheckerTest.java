import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BracketCheckerTest {

  @Test
  public void testPairedSquareBrackets() {
    BracketChecker bracketChecker = new BracketChecker("[]");
    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isTrue();
  }

  @Test
  public void testEmptyString() {
    BracketChecker bracketChecker = new BracketChecker("");
    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isTrue();
  }

  @Test
  public void testUnpairedBrackets() {
    BracketChecker bracketChecker = new BracketChecker("[[");
    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isFalse();
  }

  @Test
  public void testWrongOrderedBrackets() {
    BracketChecker bracketChecker = new BracketChecker("}{");
    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isFalse();
  }

  @Test
  public void testWrongClosingBracket() {
    BracketChecker bracketChecker = new BracketChecker("{]");
    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isFalse();
  }

  @Test
  public void testPairedWithWhitespace() {
    BracketChecker bracketChecker = new BracketChecker("{ }");
    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isTrue();
  }

  @Test
  public void testPartiallyPairedBrackets() {
    BracketChecker bracketChecker = new BracketChecker("{[])");
    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isFalse();
  }

  @Test
  public void testSimpleNestedBrackets() {
    BracketChecker bracketChecker = new BracketChecker("{[]}");
    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isTrue();
  }

  @Test
  public void testSeveralPairedBrackets() {
    BracketChecker bracketChecker = new BracketChecker("{}[]");
    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isTrue();
  }

  @Test
  public void testPairedAndNestedBrackets() {
    BracketChecker bracketChecker = new BracketChecker("([{}({}[])])");
    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isTrue();
  }

  @Test
  public void testUnopenedClosingBracket() {
    BracketChecker bracketChecker = new BracketChecker("{[)][]}");
    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isFalse();
  }

  @Test
  public void testUnpairedAndNestedBrackets() {
    BracketChecker bracketChecker = new BracketChecker("([{])");
    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isFalse();
  }

  @Test
  public void testPairedAndWrongNestedBrackets() {
    BracketChecker bracketChecker = new BracketChecker("[({]})");
    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isFalse();
  }

  @Test
  public void testPairedAndWrongNestedBracketsButInnermostAreCorrect() {
    BracketChecker bracketChecker = new BracketChecker("[({}])");
    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isFalse();
  }

  @Test
  public void testPairedAndIncompleteBrackets() {
    BracketChecker bracketChecker = new BracketChecker("{}[");
    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isFalse();
  }

  @Test
  public void testTooManyClosingBrackets() {
    BracketChecker bracketChecker = new BracketChecker("[]]");
    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isFalse();
  }

  @Test
  public void testEarlyUnexpectedBrackets() {
    BracketChecker bracketChecker = new BracketChecker(")()");
    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isFalse();
  }

  @Test
  public void testEarlyMismatchedBrackets() {
    BracketChecker bracketChecker = new BracketChecker("{)()");
    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isFalse();
  }

  @Test
  public void testMathExpression() {
    BracketChecker bracketChecker = new BracketChecker("(((185 + 223.85) * 15) - 543)/2");
    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isTrue();
  }

  @Test
  public void testComplexLatexExpression() {
    BracketChecker bracketChecker =
        new BracketChecker(
            "\\left(\\begin{array}{cc} \\frac{1}{3} & x\\\\ \\mathrm{e}^{x} &... x^2"
                + " \\end{array}\\r"
                + "ight)");

    assertThat(bracketChecker.areBracketsMatchedAndNestedCorrectly()).isTrue();
  }
}
