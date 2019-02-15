import java.util.stream.IntStream;

class ResistorColor {
	static final String[] COLORS = { "black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey",
			"white" };

	int colorCode(String color) {
		return IntStream.range(0, COLORS.length).filter(i -> COLORS[i].equals(color)).findAny().getAsInt();
	}

	String[] colors() {
		return COLORS;
	}
}
