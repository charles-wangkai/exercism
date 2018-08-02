
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class BinarySearchTest {

	@Test
	public void findsAValueInAnArrayWithOneElement() {
		List<Character> listOfUnitLength = Collections.singletonList('6');

		BinarySearch<Character> search = new BinarySearch<>(listOfUnitLength);

		assertEquals(0, search.indexOf('6'));
	}

	@Test
	public void findsAValueInTheMiddleOfAnArray() {
		List<String> sortedList = Collections.unmodifiableList(Arrays.asList("1", "3", "4", "6", "8", "9", "11"));

		BinarySearch<String> search = new BinarySearch<>(sortedList);

		assertEquals(3, search.indexOf("6"));
	}

	@Test
	public void findsAValueAtTheBeginningOfAnArray() {
		List<Integer> sortedList = Collections.unmodifiableList(Arrays.asList(1, 3, 4, 6, 8, 9, 11));

		BinarySearch<Integer> search = new BinarySearch<>(sortedList);

		assertEquals(0, search.indexOf(1));
	}

	@Test
	public void findsAValueAtTheEndOfAnArray() {
		List<Integer> sortedList = Collections.unmodifiableList(Arrays.asList(1, 3, 4, 6, 8, 9, 11));

		BinarySearch<Integer> search = new BinarySearch<>(sortedList);

		assertEquals(6, search.indexOf(11));
	}

	@Test
	public void findsAValueInAnArrayOfOddLength() {
		List<Integer> sortedListOfOddLength = Collections
				.unmodifiableList(Arrays.asList(1, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 634));

		BinarySearch<Integer> search = new BinarySearch<>(sortedListOfOddLength);

		assertEquals(9, search.indexOf(144));
	}

	@Test
	public void findsAValueInAnArrayOfEvenLength() {
		List<Integer> sortedListOfEvenLength = Collections
				.unmodifiableList(Arrays.asList(1, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377));

		BinarySearch<Integer> search = new BinarySearch<>(sortedListOfEvenLength);

		assertEquals(5, search.indexOf(21));
	}

	@Test
	public void identifiesThatAValueIsNotIncludedInTheArray() {
		List<String> sortedList = Collections.unmodifiableList(Arrays.asList("1", "3", "4", "6", "8", "9", "11"));

		BinarySearch<String> search = new BinarySearch<>(sortedList);

		assertEquals(-1, search.indexOf("7"));
	}

	@Test
	public void aValueSmallerThanTheArraysSmallestValueIsNotIncluded() {
		List<Integer> sortedList = Collections.unmodifiableList(Arrays.asList(1, 3, 4, 6, 8, 9, 11));

		BinarySearch<Integer> search = new BinarySearch<>(sortedList);

		assertEquals(-1, search.indexOf(0));
	}

	@Test
	public void aValueLargerThanTheArraysSmallestValueIsNotIncluded() {
		List<Integer> sortedList = Collections.unmodifiableList(Arrays.asList(1, 3, 4, 6, 8, 9, 11));

		BinarySearch<Integer> search = new BinarySearch<>(sortedList);

		assertEquals(-1, search.indexOf(13));
	}

	@Test
	public void nothingIsIncludedInAnEmptyArray() {
		List<Integer> emptyList = Collections.emptyList();

		BinarySearch<Integer> search = new BinarySearch<>(emptyList);

		assertEquals(-1, search.indexOf(1));
	}
}
