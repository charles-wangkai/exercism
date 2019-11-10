import java.util.Arrays;
import java.util.stream.IntStream;

class SumOfMultiples {
	int number;
	int[] set;

	SumOfMultiples(int number, int[] set) {
		this.number = number;
		this.set = set;
	}

	int getSum() {
		return IntStream.range(1, number)
				.filter(x -> Arrays.stream(set).anyMatch(divisor -> divisor != 0 && x % divisor == 0)).sum();
	}

}