import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class GrepTool {
	String grep(String pattern, List<String> flags, List<String> files) {
		StringBuilder result = new StringBuilder();

		for (String file : files) {
			boolean found = false;

			try {
				Scanner sc = new Scanner(new File(file));

				int lineNum = 1;
				while (sc.hasNextLine()) {
					String line = sc.nextLine();

					if (isSatisfied(line, pattern, flags)) {
						if (flags.contains("-l")) {
							if (!found) {
								if (result.length() != 0) {
									result.append("\n");
								}

								result.append(file);
							}
						} else {
							if (result.length() != 0) {
								result.append("\n");
							}

							if (files.size() > 1) {
								result.append(file);
								result.append(":");
							}

							if (flags.contains("-n")) {
								result.append(lineNum);
								result.append(":");
							}

							result.append(line);
						}

						found = true;
					}

					lineNum++;
				}

				sc.close();
			} catch (FileNotFoundException e) {
			}
		}

		return result.toString();
	}

	boolean isSatisfied(String line, String pattern, List<String> flags) {
		boolean match = !flags.contains("-x") || line.length() == pattern.length();
		if (match) {
			if (flags.contains("-i")) {
				line = line.toLowerCase();
				pattern = pattern.toLowerCase();
			}
			match = line.contains(pattern);
		}
		return flags.contains("-v") ? !match : match;
	}
}