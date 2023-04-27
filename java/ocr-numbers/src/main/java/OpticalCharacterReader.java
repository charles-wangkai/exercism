import java.util.List;
import java.util.stream.IntStream;

public class OpticalCharacterReader {
  static final String[] IMAGES = {
    """
     _
    | |
    |_|

    """,
    """

      |
      |

    """,
    """
     _
     _|
    |_

    """,
    """
     _
     _|
     _|

    """,
    """

    |_|
      |

    """,
    """
     _
    |_
     _|

    """,
    """
     _
    |_
    |_|

    """,
    """
     _
      |
      |

    """,
    """
     _
    |_|
    |_|

    """,
    """
     _
    |_|
     _|

    """
  };

  String parse(List<String> lines) {
    int row = lines.size();
    if (row % 4 != 0) {
      throw new IllegalArgumentException("Number of input rows must be a positive multiple of 4");
    }

    int col = lines.get(0).length();
    if (col % 3 != 0) {
      throw new IllegalArgumentException(
          "Number of input columns must be a positive multiple of 3");
    }

    StringBuilder result = new StringBuilder();
    for (int r = 0; r < row; r += 4) {
      if (result.length() != 0) {
        result.append(",");
      }

      for (int c = 0; c < col; c += 3) {
        result.append(recognize(lines, r, c));
      }
    }

    return result.toString();
  }

  char recognize(List<String> lines, int r, int c) {
    List<String> part =
        IntStream.range(r, r + 4).mapToObj(i -> lines.get(i).substring(c, c + 3)).toList();

    for (int i = 0; i < IMAGES.length; ++i) {
      if (isSame(part, IMAGES[i])) {
        return (char) (i + '0');
      }
    }

    return '?';
  }

  boolean isSame(List<String> part, String image) {
    return IntStream.range(0, part.size())
        .allMatch(i -> part.get(i).stripTrailing().equals(image.split("\n", -1)[i]));
  }
}
