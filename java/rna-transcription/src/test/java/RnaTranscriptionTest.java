import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/*
 * version: 1.0.1
 */
public class RnaTranscriptionTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private RnaTranscription rnaTranscription;

    @Before
    public void setUp() {
        rnaTranscription = new RnaTranscription();
    }

    @Test
    public void testRnaTranscriptionOfCytosineIsGuanine() {
        assertEquals("G", rnaTranscription.transcribe("C"));
    }

    @Test
    public void testRnaTranscriptionOfGuanineIsCytosine() {
        assertEquals("C", rnaTranscription.transcribe("G"));
    }

    @Test
    public void testRnaTranscriptionOfThymineIsAdenine() {
        assertEquals("A", rnaTranscription.transcribe("T"));
    }

    @Test
    public void testRnaTranscriptionOfAdenineIsUracil() {
        assertEquals("U", rnaTranscription.transcribe("A"));
    }

    @Test
    public void testRnaTranscription() {
        assertEquals("UGCACCAGAAUU", rnaTranscription.transcribe("ACGTGGTCTTAA"));
    }

    @Test
    public void testRnaTranscriptionOfRnaThrowsAnError() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid input");
        rnaTranscription.transcribe("U");
    }

    @Test
    public void testRnaTranscriptionOfInvalidInputThrowsAnError() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid input");
        rnaTranscription.transcribe("XXX");
    }

    @Test
    public void testRnaTranscriptionOfPartiallyInvalidInput() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid input");
        rnaTranscription.transcribe("ACGTXXXCTTAA");
    }

}
