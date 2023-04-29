public class RunLengthEncoding {
  String encode(String s) {
    StringBuilder result = new StringBuilder();
    char current = 0;
    int count = 0;
    for (int i = 0; i <= s.length(); ++i) {
      if (i != s.length() && s.charAt(i) == current) {
        ++count;
      } else {
        if (count != 0) {
          if (count != 1) {
            result.append(count);
          }
          result.append(current);
        }

        if (i != s.length()) {
          current = s.charAt(i);
          count = 1;
        }
      }
    }

    return result.toString();
  }

  String decode(String s) {
    StringBuilder result = new StringBuilder();
    int index = 0;
    while (index != s.length()) {
      int endIndex = index;
      while (endIndex != s.length() && Character.isDigit(s.charAt(endIndex))) {
        ++endIndex;
      }

      int count = (endIndex == index) ? 1 : Integer.parseInt(s.substring(index, endIndex));
      result.append(String.valueOf(s.charAt(endIndex)).repeat(count));

      index = endIndex + 1;
    }

    return result.toString();
  }
}
