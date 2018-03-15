enum Player {
	NONE, BLACK, WHITE;

	static Player fromCharater(char ch) {
		if (ch == 'B') {
			return BLACK;
		} else if (ch == 'W') {
			return WHITE;
		} else {
			return NONE;
		}
	}
}
