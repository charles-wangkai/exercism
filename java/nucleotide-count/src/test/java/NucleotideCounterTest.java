import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class NucleotideCounterTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testEmptyDnaStringHasNoNucleotides() {
		NucleotideCounter nucleotideCounter = new NucleotideCounter("");
		Map<Character, Integer> counts = nucleotideCounter.nucleotideCounts();
		assertThat(counts, allOf(hasEntry('A', 0), hasEntry('C', 0), hasEntry('G', 0), hasEntry('T', 0)));
	}

	@Test
	public void testDnaStringHasOneNucleotide() {
		NucleotideCounter nucleotideCounter = new NucleotideCounter("G");
		Map<Character, Integer> counts = nucleotideCounter.nucleotideCounts();
		assertThat(counts, allOf(hasEntry('A', 0), hasEntry('C', 0), hasEntry('G', 1), hasEntry('T', 0)));
	}

	@Test
	public void testRepetitiveSequenceWithOnlyGuanine() {
		NucleotideCounter nucleotideCounter = new NucleotideCounter("GGGGGGG");
		Map<Character, Integer> counts = nucleotideCounter.nucleotideCounts();
		assertThat(counts, allOf(hasEntry('A', 0), hasEntry('C', 0), hasEntry('G', 7), hasEntry('T', 0)));
	}

	@Test
	public void testDnaStringHasMultipleNucleotide() {
		NucleotideCounter nucleotideCounter = new NucleotideCounter(
				"AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC");
		Map<Character, Integer> counts = nucleotideCounter.nucleotideCounts();
		assertThat(counts, allOf(hasEntry('A', 20), hasEntry('C', 12), hasEntry('G', 17), hasEntry('T', 21)));
	}

	@Test
	public void testDnaStringHasInvalidNucleotides() {
		expectedException.expect(IllegalArgumentException.class);
		NucleotideCounter nucleotideCounter = new NucleotideCounter("AGXXACT");
	}
}
