import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BeerSong {
	String verse(int verse) {
		if (verse == 0) {
			return "No more bottles of beer on the wall, no more bottles of beer.\n"
					+ "Go to the store and buy some more, 99 bottles of beer on the wall.\n\n";
		} else if (verse == 1) {
			return "1 bottle of beer on the wall, 1 bottle of beer.\n"
					+ "Take it down and pass it around, no more bottles of beer on the wall.\n\n";
		} else {
			return String.format(
					"%d bottles of beer on the wall, %d bottles of beer.\n"
							+ "Take one down and pass it around, %d bottle%s of beer on the wall.\n\n",
					verse, verse, verse - 1, verse == 2 ? "" : "s");
		}
	}

	String sing(int startVerse, int endVerse) {
		return String.join("", IntStream.rangeClosed(0, startVerse - endVerse).map(i -> startVerse - i)
				.mapToObj(this::verse).collect(Collectors.toList()));
	}

	String singSong() {
		return sing(99, 0);
	}
}
