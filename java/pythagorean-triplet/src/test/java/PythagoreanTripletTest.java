
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class PythagoreanTripletTest {

	@Test
	public void shouldCalculateSum() {
		PythagoreanTriplet triplet = new PythagoreanTriplet(3, 4, 5);
		int expected = 12;
		int actual = triplet.calculateSum();
		assertEquals(expected, actual);
	}

	@Test
	public void shouldCalculateProduct() {
		PythagoreanTriplet triplet = new PythagoreanTriplet(3, 4, 5);
		long expected = 60l;
		long actual = triplet.calculateProduct();
		assertEquals(expected, actual);
	}

	@Test
	public void testIsPythagoreanOK() {
		PythagoreanTriplet triplet = new PythagoreanTriplet(3, 4, 5);
		assertTrue(triplet.isPythagorean());
	}

	@Test
	public void testIsPythagoreanFail() {
		PythagoreanTriplet triplet = new PythagoreanTriplet(5, 6, 7);
		assertFalse(triplet.isPythagorean());
	}

	@Test
	public void shouldMakeTripletsUpToTen() {
		List<Long> actual = PythagoreanTriplet.makeTripletsList().withFactorsLessThanOrEqualTo(10).build().stream()
				.map(t -> t.calculateProduct()).sorted().collect(Collectors.toList());
		List<Long> expected = Arrays.asList(60l, 480l);
		assertEquals(expected, actual);
	}

	@Test
	public void shouldMakeTripletsElevenToTwenty() {
		List<Long> actual = PythagoreanTriplet.makeTripletsList().withFactorsGreaterThanOrEqualTo(11)
				.withFactorsLessThanOrEqualTo(20).build().stream().map(t -> t.calculateProduct())
				.sorted((p1, p2) -> Double.compare(p1, p2)).collect(Collectors.toList());
		List<Long> expected = Arrays.asList(3840l);
		assertEquals(expected, actual);
	}

	@Test
	public void shouldMakeTripletsAndFilterOnSum() {
		List<Long> actual = PythagoreanTriplet.makeTripletsList().withFactorsLessThanOrEqualTo(100).thatSumTo(180)
				.build().stream().map(t -> t.calculateProduct()).sorted((p1, p2) -> Double.compare(p1, p2))
				.collect(Collectors.toList());
		List<Long> expected = Arrays.asList(118080l, 168480l, 202500l);
		assertEquals(expected, actual);
	}
}
