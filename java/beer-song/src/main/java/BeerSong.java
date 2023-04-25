import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BeerSong {
  String verse(int verse) {
    if (verse == 0) {
      return """
				No more bottles of beer on the wall, no more bottles of beer.
				Go to the store and buy some more, 99 bottles of beer on the wall.

				""";
    }
    if (verse == 1) {
      return """
				1 bottle of beer on the wall, 1 bottle of beer.
				Take it down and pass it around, no more bottles of beer on the wall.

				""";
    }

    return String.format(
        """
				%d bottles of beer on the wall, %d bottles of beer.
				Take one down and pass it around, %d bottle%s of beer on the wall.

				""",
        verse, verse, verse - 1, (verse == 2) ? "" : "s");
  }

  String sing(int startVerse, int verseNum) {
    return IntStream.range(0, verseNum)
        .map(i -> startVerse - i)
        .mapToObj(this::verse)
        .collect(Collectors.joining(""));
  }

  String singSong() {
    return sing(99, 100);
  }
}
