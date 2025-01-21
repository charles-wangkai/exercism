class Fighter {
  boolean isVulnerable() {
    return true;
  }

  int getDamagePoints(Fighter fighter) {
    return 1;
  }
}

class Warrior extends Fighter {
  @Override
  public String toString() {
    return "Fighter is a Warrior";
  }

  @Override
  boolean isVulnerable() {
    return false;
  }

  @Override
  int getDamagePoints(Fighter wizard) {
    return wizard.isVulnerable() ? 10 : 6;
  }
}

class Wizard extends Fighter {
  boolean spellPrepared;

  @Override
  public String toString() {
    return "Fighter is a Wizard";
  }

  @Override
  boolean isVulnerable() {
    return !spellPrepared;
  }

  @Override
  int getDamagePoints(Fighter warrior) {
    return spellPrepared ? 12 : 3;
  }

  void prepareSpell() {
    spellPrepared = true;
  }
}
