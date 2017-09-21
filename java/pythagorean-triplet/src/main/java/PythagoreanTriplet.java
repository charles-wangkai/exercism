import java.util.ArrayList;
import java.util.List;

public class PythagoreanTriplet {
	int a;
	int b;
	int c;

	PythagoreanTriplet(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
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
	int lower = 1;
	int upper;
	Integer sum;

	PythagoreanTripletBuilder withFactorsGreaterThanOrEqualTo(int lower) {
		this.lower = lower;
		return this;
	}

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
		for (int a = lower; a <= upper; a++) {
			for (int b = a + 1; b <= upper; b++) {
				for (int c = b + 1; c <= upper; c++) {
					PythagoreanTriplet pt = new PythagoreanTriplet(a, b, c);
					if (pt.isPythagorean() && (sum == null || pt.calculateSum() == sum)) {
						result.add(pt);
					}
				}
			}
		}
		return result;
	}
}