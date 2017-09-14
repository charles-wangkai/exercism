import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Allergies {
	int score;

	Allergies(int score) {
		this.score = score;
	}

	boolean isAllergicTo(Allergen item) {
		return (score & item.getScore()) != 0;
	}

	List<Allergen> getList() {
		return Arrays.stream(Allergen.values()).filter(this::isAllergicTo).collect(Collectors.toList());
	}
}
