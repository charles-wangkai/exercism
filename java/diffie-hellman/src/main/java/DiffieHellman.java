import java.math.BigInteger;
import java.util.Random;

public class DiffieHellman {
  BigInteger privateKey(BigInteger prime) {
    Random random = new Random();
    BigInteger limit = prime.subtract(BigInteger.TWO);
    while (true) {
      BigInteger r = new BigInteger(limit.bitLength(), random);
      if (r.compareTo(limit) < 0) {
        return r.add(BigInteger.TWO);
      }
    }
  }

  BigInteger publicKey(BigInteger primeA, BigInteger primeB, BigInteger privateKey) {
    return primeB.modPow(privateKey, primeA);
  }

  BigInteger secret(BigInteger prime, BigInteger publicKey, BigInteger privateKey) {
    return publicKey.modPow(privateKey, prime);
  }
}
