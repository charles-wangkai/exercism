import java.util.ArrayList;
import java.util.List;

class Proverb {
	String[] words;

	Proverb(String[] words) {
		this.words = words;
	}

	String recite() {
		List<String> sentences = new ArrayList<String>();
		for (int i = 0; i < words.length - 1; i++) {
			sentences.add(String.format("For want of a %s the %s was lost.", words[i], words[i + 1]));
		}
		if (words.length > 0) {
			sentences.add(String.format("And all for the want of a %s.", words[0]));
		}
		return String.join("\n", sentences);
	}
}