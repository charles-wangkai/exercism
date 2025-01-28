import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class RomanNumeralsTest {

  private RomanNumerals romanNumerals;

  @Test
  public void test1ToRomanNumberI() {
    romanNumerals = new RomanNumerals(1);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("I");
  }

  @Test
  public void test2ToRomanNumberII() {
    romanNumerals = new RomanNumerals(2);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("II");
  }

  @Test
  public void test3ToRomanNumberIII() {
    romanNumerals = new RomanNumerals(3);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("III");
  }

  @Test
  public void test4ToRomanNumberIV() {
    romanNumerals = new RomanNumerals(4);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("IV");
  }

  @Test
  public void test5ToRomanNumberV() {
    romanNumerals = new RomanNumerals(5);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("V");
  }

  @Test
  public void test6ToRomanNumberVI() {
    romanNumerals = new RomanNumerals(6);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("VI");
  }

  @Test
  public void test9ToRomanNumberIX() {
    romanNumerals = new RomanNumerals(9);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("IX");
  }

  @Test
  public void test16ToRomanNumberXVI() {
    romanNumerals = new RomanNumerals(16);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("XVI");
  }

  @Test
  public void test27ToRomanNumberXXVII() {
    romanNumerals = new RomanNumerals(27);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("XXVII");
  }

  @Test
  public void test48ToRomanNumberXLVIII() {
    romanNumerals = new RomanNumerals(48);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("XLVIII");
  }

  @Test
  public void test49ToRomanNumberXLIX() {
    romanNumerals = new RomanNumerals(49);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("XLIX");
  }

  @Test
  public void test59ToRomanNumberLIX() {
    romanNumerals = new RomanNumerals(59);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("LIX");
  }

  @Test
  public void test66ToRomanNumberLXVI() {
    romanNumerals = new RomanNumerals(66);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("LXVI");
  }

  @Test
  public void test93ToRomanNumberXCIII() {
    romanNumerals = new RomanNumerals(93);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("XCIII");
  }

  @Test
  public void test141ToRomanNumberCXLI() {
    romanNumerals = new RomanNumerals(141);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("CXLI");
  }

  @Test
  public void test163ToRomanNumberCLXIII() {
    romanNumerals = new RomanNumerals(163);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("CLXIII");
  }

  @Test
  public void test166ToRomanNumberCLXVI() {
    romanNumerals = new RomanNumerals(166);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("CLXVI");
  }

  @Test
  public void test402ToRomanNumberCDII() {
    romanNumerals = new RomanNumerals(402);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("CDII");
  }

  @Test
  public void test575ToRomanNumberDLXXV() {
    romanNumerals = new RomanNumerals(575);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("DLXXV");
  }

  @Test
  public void test666ToRomanNumberDCLXVI() {
    romanNumerals = new RomanNumerals(666);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("DCLXVI");
  }

  @Test
  public void test911ToRomanNumberCMXI() {
    romanNumerals = new RomanNumerals(911);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("CMXI");
  }

  @Test
  public void test1024ToRomanNumberMXXIV() {
    romanNumerals = new RomanNumerals(1024);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("MXXIV");
  }

  @Test
  public void test1666ToRomanNumberMDCLXVI() {
    romanNumerals = new RomanNumerals(1666);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("MDCLXVI");
  }

  @Test
  public void test3000ToRomanNumberMMM() {
    romanNumerals = new RomanNumerals(3000);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("MMM");
  }

  @Test
  public void test3001ToRomanNumberMMMI() {
    romanNumerals = new RomanNumerals(3001);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("MMMI");
  }

  @Test
  public void test3888ToRomanNumberMMMDCCCLXXXVIII() {
    romanNumerals = new RomanNumerals(3888);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("MMMDCCCLXXXVIII");
  }

  @Test
  public void test3999ToRomanNumberMMMCMXCIX() {
    romanNumerals = new RomanNumerals(3999);
    assertThat(romanNumerals.getRomanNumeral()).isEqualTo("MMMCMXCIX");
  }
}
