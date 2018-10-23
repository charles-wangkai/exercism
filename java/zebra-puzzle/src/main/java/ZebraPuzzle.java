import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ZebraPuzzle {
	static List<Color> colorSolution;
	static List<Nationality> nationalitySolution;
	static List<Drink> drinkSolution;
	static List<Smoke> smokeSolution;
	static List<Pet> petSolution;

	static {
		init();
	}

	static void init() {
		List<List<Color>> colorPermutations = buildColorPermutations();
		List<List<Nationality>> nationalityPermutations = buildNationalityPermutations();
		List<List<Drink>> drinkPermutations = buildDrinkPermutations();
		List<List<Smoke>> smokePermutations = buildSmokePermutations();
		List<List<Pet>> petPermutations = buildPetPermutations();

		for (List<Color> colorPermutation : colorPermutations) {
			for (List<Nationality> nationalityPermutation : nationalityPermutations) {
				for (List<Drink> drinkPermutation : drinkPermutations) {
					for (List<Smoke> smokePermutation : smokePermutations) {
						for (List<Pet> petPermutation : petPermutations) {
							if (check(colorPermutation, nationalityPermutation, drinkPermutation, smokePermutation,
									petPermutation)) {
								colorSolution = colorPermutation;
								nationalitySolution = nationalityPermutation;
								drinkSolution = drinkPermutation;
								smokeSolution = smokePermutation;
								petSolution = petPermutation;

								return;
							}
						}
					}
				}
			}
		}
	}

	static boolean check(List<Color> colorPermutation, List<Nationality> nationalityPermutation,
			List<Drink> drinkPermutation, List<Smoke> smokePermutation, List<Pet> petPermutation) {
		return nationalityPermutation.indexOf(Nationality.ENGLISHMAN) == colorPermutation.indexOf(Color.RED)
				&& nationalityPermutation.indexOf(Nationality.SPANIARD) == petPermutation.indexOf(Pet.DOG)
				&& drinkPermutation.indexOf(Drink.COFFEE) == colorPermutation.indexOf(Color.GREEN)
				&& nationalityPermutation.indexOf(Nationality.UKRAINIAN) == drinkPermutation.indexOf(Drink.TEA)
				&& colorPermutation.indexOf(Color.GREEN) == colorPermutation.indexOf(Color.IVORY) + 1
				&& smokePermutation.indexOf(Smoke.OLD_GOLD) == petPermutation.indexOf(Pet.SNAILS)
				&& smokePermutation.indexOf(Smoke.KOOLS) == colorPermutation.indexOf(Color.YELLOW)
				&& Math.abs(smokePermutation.indexOf(Smoke.CHESTERFIELDS) - petPermutation.indexOf(Pet.FOX)) == 1
				&& Math.abs(smokePermutation.indexOf(Smoke.KOOLS) - petPermutation.indexOf(Pet.HORSE)) == 1
				&& smokePermutation.indexOf(Smoke.LUCKY_STRIKE) == drinkPermutation.indexOf(Drink.ORANGE_JUICE)
				&& nationalityPermutation.indexOf(Nationality.JAPANESE) == smokePermutation.indexOf(Smoke.PARLIAMENTS);
	}

	static List<List<Color>> buildColorPermutations() {
		return generatePermutations(Color.class).stream().filter(permutation -> permutation.get(1) == Color.BLUE)
				.collect(Collectors.toList());
	}

	static List<List<Nationality>> buildNationalityPermutations() {
		return generatePermutations(Nationality.class).stream()
				.filter(permutation -> permutation.get(0) == Nationality.NORWEGIAN).collect(Collectors.toList());
	}

	static List<List<Drink>> buildDrinkPermutations() {
		return generatePermutations(Drink.class).stream().filter(permutation -> permutation.get(2) == Drink.MILK)
				.collect(Collectors.toList());
	}

	static List<List<Smoke>> buildSmokePermutations() {
		return generatePermutations(Smoke.class);
	}

	static List<List<Pet>> buildPetPermutations() {
		return generatePermutations(Pet.class);
	}

	static <T> List<List<T>> generatePermutations(Class<T> enumClass) {
		T[] values = enumClass.getEnumConstants();

		List<List<T>> permutations = new ArrayList<>();
		search(permutations, values, 0);
		return permutations;
	}

	static <T> void search(List<List<T>> permutations, T[] values, int index) {
		if (index == values.length) {
			permutations.add(new ArrayList<>(Arrays.asList(values)));

			return;
		}

		for (int i = index; i < values.length; i++) {
			swap(values, index, i);
			search(permutations, values, index + 1);
			swap(values, index, i);
		}
	}

	static <T> void swap(T[] values, int index1, int index2) {
		T temp = values[index1];
		values[index1] = values[index2];
		values[index2] = temp;
	}

	String getWaterDrinker() {
		return nationalitySolution.get(drinkSolution.indexOf(Drink.WATER)).name;
	}

	String getZebraOwner() {
		return nationalitySolution.get(petSolution.indexOf(Pet.ZEBRA)).name;
	}
}

enum Color {
	RED, GREEN, IVORY, YELLOW, BLUE
}

enum Nationality {
	ENGLISHMAN("Englishman"), SPANIARD("Spaniard"), UKRAINIAN("Ukrainian"), NORWEGIAN("Norwegian"),
	JAPANESE("Japanese");

	String name;

	Nationality(String name) {
		this.name = name;
	}
}

enum Drink {
	COFFEE, TEA, MILK, ORANGE_JUICE, WATER
}

enum Smoke {
	OLD_GOLD, KOOLS, CHESTERFIELDS, LUCKY_STRIKE, PARLIAMENTS
}

enum Pet {
	DOG, SNAILS, FOX, HORSE, ZEBRA
}