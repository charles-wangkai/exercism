import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class AtbashTest {

  private Atbash atbash;

  @Before
  public void setup() {
    atbash = new Atbash();
  }

  @Test
  public void testEncodeYes() {
    assertThat(atbash.encode("yes")).isEqualTo("bvh");
  }

  @Test
  public void testEncodeNo() {
    assertThat(atbash.encode("no")).isEqualTo("ml");
  }

  @Test
  public void testEncodeOmgInCapital() {
    assertThat(atbash.encode("OMG")).isEqualTo("lnt");
  }

  @Test
  public void testEncodeOmgWithSpaces() {
    assertThat(atbash.encode("O M G")).isEqualTo("lnt");
  }

  @Test
  public void testEncodeMindBlowingly() {
    assertThat(atbash.encode("mindblowingly")).isEqualTo("nrmwy oldrm tob");
  }

  @Test
  public void testEncodeNumbers() {
    assertThat(atbash.encode("Testing,1 2 3, testing.")).isEqualTo("gvhgr mt123 gvhgr mt");
  }

  @Test
  public void testEncodeDeepThought() {
    assertThat(atbash.encode("Truth is fiction.")).isEqualTo("gifgs rhurx grlm");
  }

  @Test
  public void testEncodeAllTheLetters() {
    assertThat(atbash.encode("The quick brown fox jumps over the lazy dog."))
        .isEqualTo("gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt");
  }

  @Test
  public void testDecodeExercism() {
    assertThat(atbash.decode("vcvix rhn")).isEqualTo("exercism");
  }

  @Test
  public void testDecodeASentence() {
    assertThat(atbash.decode("zmlyh gzxov rhlug vmzhg vkkrm thglm v"))
        .isEqualTo("anobstacleisoftenasteppingstone");
  }

  @Test
  public void testDecodeNumbers() {
    assertThat(atbash.decode("gvhgr mt123 gvhgr mt")).isEqualTo("testing123testing");
  }

  @Test
  public void testDecodeAllTheLetters() {
    assertThat(atbash.decode("gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt"))
        .isEqualTo("thequickbrownfoxjumpsoverthelazydog");
  }

  @Test
  public void testDecodeWithTooManySpaces() {
    assertThat(atbash.decode("vc vix    r hn")).isEqualTo("exercism");
  }

  @Test
  public void testDecodeWithNoSpaces() {
    assertThat(atbash.decode("zmlyhgzxovrhlugvmzhgvkkrmthglmv"))
        .isEqualTo("anobstacleisoftenasteppingstone");
  }
}
