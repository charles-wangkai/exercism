import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SgfParsing {
  public SgfNode parse(String input) throws SgfParsingException {
    if (!(input.startsWith("(") && input.endsWith(")"))) {
      throw new SgfParsingException("Must enclosed with parentheses!");
    }

    String s = input.substring(1, input.length() - 1);
    if (!s.startsWith(";")) {
      throw new SgfParsingException("Tree with no nodes!");
    }

    s = s.substring(1);

    if (s.isEmpty()) {
      return new SgfNode();
    }

    if (!s.contains("[")) {
      throw new SgfParsingException("Properties without delimiter!");
    }

    Map<String, List<String>> properties = new HashMap<>();
    while (!s.isEmpty() && ";(".indexOf(s.charAt(0)) == -1) {
      int beginIndex = find(s, "[", 0);
      String propertyId = s.substring(0, beginIndex);
      if (propertyId.chars().anyMatch(c -> !Character.isUpperCase(c))) {
        throw new SgfParsingException("Property id must be uppercase!");
      }

      properties.put(propertyId, new ArrayList<>());
      while (beginIndex < s.length() && s.charAt(beginIndex) == '[') {
        int endIndex = find(s, "]", beginIndex);
        properties.get(propertyId).add(escape(s.substring(beginIndex + 1, endIndex)));

        beginIndex = endIndex + 1;
      }

      s = s.substring(beginIndex);
    }

    List<SgfNode> children = new ArrayList<>();
    int index = 0;
    while (index < s.length()) {
      int endIndex;
      if (s.charAt(index) == ';') {
        endIndex = find(s, ";(", index + 1);
        endIndex = ((endIndex == -1) ? s.length() : endIndex) - 1;

        children.add(parse("(" + s.substring(index, endIndex + 1) + ")"));
      } else {
        endIndex = find(s, ")", index);

        children.add(parse(s.substring(index, endIndex + 1)));
      }

      index = endIndex + 1;
    }

    return new SgfNode(properties, children);
  }

  String escape(String s) {
    StringBuilder result = new StringBuilder();
    int index = 0;
    while (index < s.length()) {
      if (s.startsWith("\\\\", index)) {
        result.append("\\");
        index += 2;
      } else if (s.startsWith("\t", index)) {
        result.append(" ");
        ++index;
      } else if (s.startsWith("\\\t", index)) {
        result.append(" ");
        index += 2;
      } else if (s.startsWith("\\\n", index)) {
        index += 2;
      } else if (s.startsWith("\\", index)) {
        ++index;
      } else {
        result.append(s.charAt(index));
        ++index;
      }
    }

    return result.toString();
  }

  int find(String s, String target, int offset) {
    int index = offset;
    while (index < s.length()) {
      if (target.indexOf(s.charAt(index)) != -1) {
        return index;
      }

      index += s.startsWith("\\", index) ? 2 : 1;
    }

    return -1;
  }
}
