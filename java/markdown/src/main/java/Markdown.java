class Markdown {
	String parse(String s) {
		StringBuilder result = new StringBuilder();
		boolean activeList = false;

		for (String line : s.split("\n")) {
			String parsedLine;
			if (isListItem(line)) {
				if (!activeList) {
					result.append("<ul>");
					activeList = true;
				}

				parsedLine = parseListItem(line);
			} else {
				if (activeList) {
					result.append("</ul>");
					activeList = false;
				}

				if (isHeader(line)) {
					parsedLine = parseHeader(line);
				} else {
					parsedLine = parseParagraph(line);
				}
			}

			result.append(parsedLine);
		}

		if (activeList) {
			result.append("</ul>");
		}

		return result.toString();
	}

	private boolean isListItem(String line) {
		return line.startsWith("*");
	}

	private boolean isHeader(String line) {
		return line.startsWith("#");
	}

	private String parseHeader(String line) {
		int count = (int) line.chars().filter(ch -> ch == '#').count();
		return String.format("<h%d>%s</h%d>", count, line.substring(count + 1), count);
	}

	private String parseParagraph(String line) {
		return "<p>" + parseSomeSymbols(line) + "</p>";
	}

	private String parseListItem(String line) {
		return "<li>" + parseSomeSymbols(line.substring(2)) + "</li>";
	}

	private String parseSomeSymbols(String line) {
		return line.replaceAll("__(.+)__", "<strong>$1</strong>").replaceAll("_(.+)_", "<em>$1</em>");
	}
}