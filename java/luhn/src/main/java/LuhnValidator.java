import java.util.stream.IntStream;

class LuhnValidator {
  boolean isValid(String candidate) {
    candidate = candidate.replace(" ", "");

    String candidate_ = candidate;
    return candidate.length() >= 2
        && candidate.chars().allMatch(Character::isDigit)
        && IntStream.range(0, candidate.length())
                    .map(
                        i -> {
                          int digit = candidate_.charAt(candidate_.length() - 1 - i) - '0';

                          return (i % 2 == 0) ? digit : ((digit * 2 - 1) % 9 + 1);
                        })
                    .sum()
                % 10
            == 0;
  }
}
