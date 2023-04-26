import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

class DnDCharacter {
  private Random random = new Random();
  private int strength = ability(rollDice());
  private int dexterity = ability(rollDice());
  private int constitution = ability(rollDice());
  private int intelligence = ability(rollDice());
  private int wisdom = ability(rollDice());
  private int charisma = ability(rollDice());

  public int getStrength() {
    return strength;
  }

  public int getDexterity() {
    return dexterity;
  }

  public int getConstitution() {
    return constitution;
  }

  public int getIntelligence() {
    return intelligence;
  }

  public int getWisdom() {
    return wisdom;
  }

  public int getCharisma() {
    return charisma;
  }

  public int getHitpoints() {
    return 10 + modifier(constitution);
  }

  public int ability(List<Integer> rolls) {
    return rolls.stream()
        .sorted(Comparator.reverseOrder())
        .limit(3)
        .mapToInt(Integer::intValue)
        .sum();
  }

  public int modifier(int value) {
    return Math.floorDiv(value - 10, 2);
  }

  public List<Integer> rollDice() {
    return IntStream.range(0, 4).map(i -> random.nextInt(6) + 1).boxed().toList();
  }
}
