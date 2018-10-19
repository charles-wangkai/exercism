import java.util.ArrayList;
import java.util.List;

public class Atbash {
	String encode(String plaintext) {
		List<Integer> letters = plaintext.toLowerCase().chars().filter(Character::isLetterOrDigit)
				.map(ch -> Character.isDigit(ch) ? ch : ('z' - ch + 'a'))
				.collect(ArrayList<Integer>::new, List::add, List::addAll);
		StringBuilder ciphertext = new StringBuilder();
		for (int i = 0; i < letters.size(); i++) {
			if (i > 0 && i % 5 == 0) {
				ciphertext.append(" ");
			}
			ciphertext.append((char) letters.get(i).intValue());
		}
		return ciphertext.toString();
	}

	String decode(String ciphertext) {
		return ciphertext.replace(" ", "").chars()
				.mapToObj(ch -> (char) (Character.isDigit(ch) ? ch : ('z' - ch + 'a')))
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	}
}