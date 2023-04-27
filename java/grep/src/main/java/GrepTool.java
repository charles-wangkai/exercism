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
        int lineNum = 1;
        try (Scanner sc = new Scanner(new File(file))) {
          while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (isSatisfied(line, pattern, flags)) {
              if (flags.contains("-l")) {
                if (!found) {
                  if (!result.isEmpty()) {
                    result.append("\n");
                  }

                  result.append(file);
                }
              } else {
                if (!result.isEmpty()) {
                  result.append("\n");
                }

                if (files.size() > 1) {
                  result.append(file).append(":");
                }

                if (flags.contains("-n")) {
                  result.append(lineNum).append(":");
                }

                result.append(line);
              }

              found = true;
            }

            ++lineNum;
          }
        }
      } catch (FileNotFoundException e) {
      }
    }

    return result.toString();
  }

  boolean isSatisfied(String line, String pattern, List<String> flags) {
    boolean result = !flags.contains("-x") || line.length() == pattern.length();
    if (result) {
      if (flags.contains("-i")) {
        line = line.toLowerCase();
        pattern = pattern.toLowerCase();
      }
      result = line.contains(pattern);
    }
    if (flags.contains("-v")) {
      result ^= true;
    }

    return result;
  }
}
