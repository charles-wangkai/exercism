import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class AnagramTest {

	@Test
	public void testNoMatches() {
		Anagram detector = new Anagram("diaper");
		assertTrue(detector.match(Arrays.asList("hello", "world", "zombies", "pants")).isEmpty());
	}

	@Test
	public void testDetectMultipleAnagrams() {
		Anagram detector = new Anagram("master");
		List<String> anagrams = detector.match(Arrays.asList("stream", "pigeon", "maters"));
		assertThat(anagrams, allOf(hasItem("maters"), hasItem("stream")));
	}

	@Test
	public void testEliminateAnagramSubsets() {
		Anagram detector = new Anagram("good");
		assertTrue(detector.match(Arrays.asList("dog", "goody")).isEmpty());
	}

	@Test
	public void testDetectLongerAnagram() {
		Anagram detector = new Anagram("listen");
		List<String> anagrams = detector.match(Arrays.asList("enlists", "google", "inlets", "banana"));
		assertThat(anagrams, hasItem("inlets"));
	}

	@Test
	public void testDetectMultipleAnagramsForLongerWord() {
		Anagram detector = new Anagram("allergy");
		List<String> anagrams = detector
				.match(Arrays.asList("gallery", "ballerina", "regally", "clergy", "largely", "leading"));
		assertThat(anagrams, allOf(hasItem("gallery"), hasItem("regally"), hasItem("largely")));
	}

	@Test
	public void testDetectsMultipleAnagramsWithDifferentCase() {
		Anagram detector = new Anagram("nose");
		List<String> anagrams = detector.match(Arrays.asList("Eons", "ONES"));
		assertThat(anagrams, allOf(hasItem("Eons"), hasItem("ONES")));
	}

	@Test
	public void testEliminateAnagramsWithSameChecksum() {
		Anagram detector = new Anagram("mass");
		assertTrue(detector.match(Collections.singletonList("last")).isEmpty());
	}

	@Test
	public void testCaseInsensitiveWhenBothAnagramAndSubjectStartWithUpperCaseLetter() {
		Anagram detector = new Anagram("Orchestra");
		List<String> anagrams = detector.match(Arrays.asList("cashregister", "Carthorse", "radishes"));
		assertThat(anagrams, hasItem("Carthorse"));
	}

	@Test
	public void testCaseInsensitiveWhenSubjectStartsWithUpperCaseLetter() {
		Anagram detector = new Anagram("Orchestra");
		List<String> anagrams = detector.match(Arrays.asList("cashregister", "carthorse", "radishes"));
		assertThat(anagrams, hasItem("carthorse"));
	}

	@Test
	public void testCaseInsensitiveWhenAnagramStartsWithUpperCaseLetter() {
		Anagram detector = new Anagram("orchestra");
		List<String> anagrams = detector.match(Arrays.asList("cashregister", "Carthorse", "radishes"));
		assertThat(anagrams, hasItem("Carthorse"));
	}

	@Test
	public void testIdenticalWordRepeatedIsNotAnagram() {
		Anagram detector = new Anagram("go");
		assertTrue(detector.match(Collections.singletonList("go Go GO")).isEmpty());
	}

	@Test
	public void testAnagramMustUseAllLettersExactlyOnce() {
		Anagram detector = new Anagram("tapper");
		assertTrue(detector.match(Collections.singletonList("patter")).isEmpty());
	}

	@Test
	public void testWordsAreNotAnagramsOfThemselvesCaseInsensitive() {
		Anagram detector = new Anagram("BANANA");
		assertTrue(detector.match(Arrays.asList("BANANA", "Banana", "banana")).isEmpty());
	}

	@Test
	public void testWordsOtherThanThemselvesCanBeAnagrams() {
		Anagram detector = new Anagram("LISTEN");
		List<String> anagrams = detector.match(Arrays.asList("Listen", "Silent", "LISTEN"));
		assertThat(anagrams, hasItem("Silent"));
	}

}
