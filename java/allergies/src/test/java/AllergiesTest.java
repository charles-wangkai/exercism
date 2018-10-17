import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class AllergiesTest {

	@Test
	public void noAllergiesMeansNotAllergicToAnything() {
		Allergies allergies = new Allergies(0);

		assertFalse(allergies.isAllergicTo(Allergen.PEANUTS));
		assertFalse(allergies.isAllergicTo(Allergen.CATS));
		assertFalse(allergies.isAllergicTo(Allergen.STRAWBERRIES));
	}

	@Test
	public void allergicToEggs() {
		Allergies allergies = new Allergies(1);

		assertTrue(allergies.isAllergicTo(Allergen.EGGS));
	}

	@Test
	public void allergicToEggsInAdditionToOtherStuff() {
		Allergies allergies = new Allergies(5);

		assertTrue(allergies.isAllergicTo(Allergen.EGGS));
		assertTrue(allergies.isAllergicTo(Allergen.SHELLFISH));
		assertFalse(allergies.isAllergicTo(Allergen.STRAWBERRIES));
	}

	@Test
	public void allergicToStrawberriesButNotPeanuts() {
		Allergies allergies = new Allergies(9);

		assertTrue(allergies.isAllergicTo(Allergen.EGGS));
		assertFalse(allergies.isAllergicTo(Allergen.PEANUTS));
		assertFalse(allergies.isAllergicTo(Allergen.SHELLFISH));
		assertTrue(allergies.isAllergicTo(Allergen.STRAWBERRIES));
	}

	@Test
	public void noAllergies() {
		Allergies allergies = new Allergies(0);

		assertEquals(0, allergies.getList().size());
	}

	@Test
	public void isAllergicToJustEggs() {
		Allergies allergies = new Allergies(1);
		List<Allergen> expectedAllergens = Collections.singletonList(Allergen.EGGS);

		assertEquals(expectedAllergens, allergies.getList());
	}

	@Test
	public void isAllergicToJustPeanuts() {
		Allergies allergies = new Allergies(2);
		List<Allergen> expectedAllergens = Collections.singletonList(Allergen.PEANUTS);

		assertEquals(expectedAllergens, allergies.getList());
	}

	@Test
	public void isAllergicToJustStrawberries() {
		Allergies allergies = new Allergies(8);
		List<Allergen> expectedAllergens = Collections.singletonList(Allergen.STRAWBERRIES);

		assertEquals(expectedAllergens, allergies.getList());
	}

	@Test
	public void isAllergicToEggsAndPeanuts() {
		Allergies allergies = new Allergies(3);
		List<Allergen> expectedAllergens = Arrays.asList(Allergen.EGGS, Allergen.PEANUTS);

		assertEquals(expectedAllergens, allergies.getList());
	}

	@Test
	public void isAllergicToEggsAndShellfish() {
		Allergies allergies = new Allergies(5);
		List<Allergen> expectedAllergens = Arrays.asList(Allergen.EGGS, Allergen.SHELLFISH);

		assertEquals(expectedAllergens, allergies.getList());
	}

	@Test
	public void isAllergicToLotsOfStuff() {
		Allergies allergies = new Allergies(248);
		List<Allergen> expectedAllergens = Arrays.asList(Allergen.STRAWBERRIES, Allergen.TOMATOES, Allergen.CHOCOLATE,
				Allergen.POLLEN, Allergen.CATS);

		assertEquals(expectedAllergens, allergies.getList());
	}

	@Test
	public void isAllergicToEverything() {
		Allergies allergies = new Allergies(255);
		List<Allergen> expectedAllergens = Arrays.asList(Allergen.EGGS, Allergen.PEANUTS, Allergen.SHELLFISH,
				Allergen.STRAWBERRIES, Allergen.TOMATOES, Allergen.CHOCOLATE, Allergen.POLLEN, Allergen.CATS);

		assertEquals(expectedAllergens, allergies.getList());
	}

	@Test
	public void ignoreNonAllergenScoreParts() {
		Allergies allergies = new Allergies(509);
		List<Allergen> expectedAllergens = Arrays.asList(Allergen.EGGS, Allergen.SHELLFISH, Allergen.STRAWBERRIES,
				Allergen.TOMATOES, Allergen.CHOCOLATE, Allergen.POLLEN, Allergen.CATS);

		assertEquals(expectedAllergens, allergies.getList());
	}
}
