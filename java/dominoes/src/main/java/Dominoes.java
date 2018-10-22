import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dominoes {
	boolean found;
	List<Domino> solution;

	List<Domino> formChain(List<Domino> dominoes) throws ChainNotFoundException {
		if (dominoes.isEmpty()) {
			return Collections.emptyList();
		}

		found = false;
		search(dominoes, 0);

		if (found) {
			return solution;
		} else {
			throw new ChainNotFoundException("No domino chain found.");
		}
	}

	void search(List<Domino> dominoes, int index) {
		solution = new ArrayList<Domino>();

		if (index == dominoes.size()) {
			Domino firstDomino = dominoes.get(0);

			int lastPrev = firstDomino.getLeft();
			int prev = firstDomino.getRight();
			solution.add(firstDomino);

			for (int i = 1; i < dominoes.size(); i++) {
				Domino domino = dominoes.get(i);

				if (domino.getLeft() == prev) {
					prev = domino.getRight();
					solution.add(domino);
				} else if (domino.getRight() == prev) {
					prev = domino.getLeft();
					solution.add(new Domino(domino.getRight(), domino.getLeft()));
				} else {
					return;
				}
			}
			if (prev == lastPrev) {
				found = true;
			}

			return;
		}

		for (int i = index; i < dominoes.size(); i++) {
			swap(dominoes, i, index);

			search(dominoes, index + 1);
			if (found) {
				break;
			}

			swap(dominoes, i, index);
		}
	}

	void swap(List<Domino> dominoes, int index1, int index2) {
		Domino temp = dominoes.get(index1);
		dominoes.set(index1, dominoes.get(index2));
		dominoes.set(index2, temp);
	}
}