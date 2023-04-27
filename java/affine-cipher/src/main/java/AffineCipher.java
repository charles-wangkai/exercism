public class AffineCipher {
  static final int M = 26;

  String encode(String plain, int a, int b) {
    checkValidity(a);

    StringBuilder result = new StringBuilder();
    for (char c : plain.toLowerCase().toCharArray()) {
      if (Character.isLetterOrDigit(c)) {
        char encoded = Character.isLetter(c) ? (char) ((a * (c - 'a') + b) % M + 'a') : c;

        if (result.length() % 6 == 5) {
          result.append(' ');
        }
        result.append(encoded);
      }
    }

    return result.toString();
  }

  String decode(String cipher, int a, int b) {
    checkValidity(a);

    StringBuilder result = new StringBuilder();
    for (char c : cipher.toCharArray()) {
      if (Character.isLetterOrDigit(c)) {
        char decoded =
            Character.isLetter(c)
                ? (char) (multiplyMod(modInverse(a), ((c - 'a') - b) % M + M) % M + 'a')
                : c;

        result.append(decoded);
      }
    }

    return result.toString();
  }

  int modInverse(int x) {
    for (int i = 1; ; ++i) {
      if (multiplyMod(x, i) == 1) {
        return i;
      }
    }
  }

  int multiplyMod(int x, int y) {
    return x * y % M;
  }

  void checkValidity(int a) {
    if (gcd(a, M) != 1) {
      throw new IllegalArgumentException("Error: keyA and alphabet size must be coprime.");
    }
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}
