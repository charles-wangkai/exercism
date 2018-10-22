import java.math.BigInteger;
import java.util.Random;

public class DiffieHellman {
	BigInteger privateKey(BigInteger prime) {
		Random random = new Random();
		BigInteger limit = prime.subtract(BigInteger.valueOf(2));

		BigInteger r;
		do {
			r = new BigInteger(limit.bitLength(), random);
		} while (r.compareTo(limit) >= 0);

		return r.add(BigInteger.valueOf(2));
	}

	BigInteger publicKey(BigInteger primeA, BigInteger primeB, BigInteger privateKey) {
		return primeB.modPow(privateKey, primeA);
	}

	BigInteger secret(BigInteger prime, BigInteger publicKey, BigInteger privateKey) {
		return publicKey.modPow(privateKey, prime);
	}
}