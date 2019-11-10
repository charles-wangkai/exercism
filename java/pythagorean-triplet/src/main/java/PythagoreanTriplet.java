import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PythagoreanTriplet {
	int a;
	int b;
	int c;

	PythagoreanTriplet(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public int hashCode() {
		return Objects.hash(a, b, c);
	}

	@Override
	public boolean equals(Object obj) {
		PythagoreanTriplet other = (PythagoreanTriplet) obj;
		return a == other.a && b == other.b && c == other.c;
	}

	int calculateSum() {
		return a + b + c;
	}

	long calculateProduct() {
		return a * b * c;
	}

	boolean isPythagorean() {
		return a * a + b * b == c * c;
	}

	static PythagoreanTripletBuilder makeTripletsList() {
		return new PythagoreanTripletBuilder();
	}
}

class PythagoreanTripletBuilder {
	int upper;
	Integer sum;

	PythagoreanTripletBuilder withFactorsLessThanOrEqualTo(int upper) {
		this.upper = upper;
		return this;
	}

	PythagoreanTripletBuilder thatSumTo(int sum) {
		this.sum = sum;
		return this;
	}

	List<PythagoreanTriplet> build() {
		List<PythagoreanTriplet> result = new ArrayList<PythagoreanTriplet>();
		for (int a = 1; a <= upper; a++) {
			for (int b = a + 1; b <= upper; b++) {
				int c = (int) Math.round(Math.sqrt(a * a + b * b));

				if (c > upper) {
					break;
				}

				PythagoreanTriplet pt = new PythagoreanTriplet(a, b, c);
				if (pt.isPythagorean() && (sum == null || pt.calculateSum() == sum)) {
					result.add(pt);
				}
			}
		}

		return result;
	}
}