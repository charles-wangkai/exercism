import random


class Character:
    def __init__(self):
        self.strength = self.ability()
        self.dexterity = self.ability()
        self.constitution = self.ability()
        self.intelligence = self.ability()
        self.wisdom = self.ability()
        self.charisma = self.ability()
        self.hitpoints = 10 + modifier(self.constitution)

    def roll_dice(self):
        return random.randint(1, 6)

    def ability(self):
        return sum(sorted(self.roll_dice() for _ in range(4))[1:])


def modifier(value):
    return (value - 10) // 2
