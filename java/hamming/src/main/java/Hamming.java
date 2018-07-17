import java.util.stream.IntStream;

public class Hamming {
	String leftStrand;
	String rightStrand;

	Hamming(String leftStrand, String rightStrand) {
		if (leftStrand.length() != rightStrand.length()) {
			throw new IllegalArgumentException("leftStrand and rightStrand must be of equal length.");
		}

		this.leftStrand = leftStrand;
		this.rightStrand = rightStrand;
	}

	int getHammingDistance() {
		return (int) IntStream.range(0, leftStrand.length()).filter(i -> leftStrand.charAt(i) != rightStrand.charAt(i))
				.count();
	}

}