import java.util.stream.Collectors;

public class PhoneNumber {
  String number;

  PhoneNumber(String s) {
    for (char c : s.toCharArray()) {
      if (!Character.isDigit(c) && " ()-.+".indexOf(c) == -1) {
        if (Character.isLetter(c)) {
          throw new IllegalArgumentException("letters not permitted");
        } else {
          throw new IllegalArgumentException("punctuations not permitted");
        }
      }
    }

    String digits =
        s.chars()
            .filter(Character::isDigit)
            .mapToObj(c -> (char) c)
            .map(String::valueOf)
            .collect(Collectors.joining());
    if (digits.length() > 11) {
      throw new IllegalArgumentException("more than 11 digits");
    }
    if (digits.length() != 10 && digits.length() != 11) {
      throw new IllegalArgumentException("incorrect number of digits");
    }
    if (digits.length() == 11 && digits.charAt(0) != '1') {
      throw new IllegalArgumentException("11 digits must start with 1");
    }

    number = digits.substring(digits.length() - 10);
    if (number.charAt(0) == '0') {
      throw new IllegalArgumentException("area code cannot start with zero");
    }
    if (number.charAt(0) == '1') {
      throw new IllegalArgumentException("area code cannot start with one");
    }
    if (number.charAt(3) == '0') {
      throw new IllegalArgumentException("exchange code cannot start with zero");
    }
    if (number.charAt(3) == '1') {
      throw new IllegalArgumentException("exchange code cannot start with one");
    }
  }

  String getNumber() {
    return number;
  }
}
