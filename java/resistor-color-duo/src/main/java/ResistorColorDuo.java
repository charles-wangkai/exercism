import java.util.HashMap;
import java.util.Map;

class ResistorColorDuo {
	static final Map<String, Integer> COLOR_TO_DIGIT = new HashMap<>();
	static {
		COLOR_TO_DIGIT.put("black", 0);
		COLOR_TO_DIGIT.put("brown", 1);
		COLOR_TO_DIGIT.put("red", 2);
		COLOR_TO_DIGIT.put("orange", 3);
		COLOR_TO_DIGIT.put("yellow", 4);
		COLOR_TO_DIGIT.put("green", 5);
		COLOR_TO_DIGIT.put("blue", 6);
		COLOR_TO_DIGIT.put("violet", 7);
		COLOR_TO_DIGIT.put("grey", 8);
		COLOR_TO_DIGIT.put("white", 9);
	}

	int value(String[] colors) {
		return COLOR_TO_DIGIT.get(colors[0]) * 10 + COLOR_TO_DIGIT.get(colors[1]);
	}
}
