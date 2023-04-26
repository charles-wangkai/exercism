import java.util.Random;
import java.util.stream.IntStream;

class DnDCharacter {
  private Random random = new Random();
  private int strength = ability();
  private int dexterity = ability();
  private int constitution = ability();
  private int intelligence = ability();
  private int wisdom = ability();
  private int charisma = ability();

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

  private int rollDice() {
    return random.nextInt(6) + 1;
  }

  public int ability() {
    return IntStream.range(0, 4).map(i -> rollDice()).sorted().skip(1).sum();
  }

  public int modifier(int value) {
    return Math.floorDiv(value - 10, 2);
  }
}
