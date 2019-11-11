# Score categories

from abc import ABC, abstractmethod


class Category(ABC):
    @staticmethod
    @abstractmethod
    def check(dice):
        pass

    @staticmethod
    @abstractmethod
    def score(dice):
        pass


class ONES(Category):
    def check(dice):
        return True

    def score(dice):
        return sum(filter(lambda x: x == 1, dice))


class TWOS(Category):
    def check(dice):
        return True

    def score(dice):
        return sum(filter(lambda x: x == 2, dice))


class THREES(Category):
    def check(dice):
        return True

    def score(dice):
        return sum(filter(lambda x: x == 3, dice))


class FOURS(Category):
    def check(dice):
        return True

    def score(dice):
        return sum(filter(lambda x: x == 4, dice))


class FIVES(Category):
    def check(dice):
        return True

    def score(dice):
        return sum(filter(lambda x: x == 5, dice))


class SIXES(Category):
    def check(dice):
        return True

    def score(dice):
        return sum(filter(lambda x: x == 6, dice))


class FULL_HOUSE(Category):
    def check(dice):
        return dice[0] == dice[1] and dice[3] == dice[4] and dice[0] != dice[4] and (dice[2] == dice[1] or dice[2] == dice[3])

    def score(dice):
        return sum(dice)


class FOUR_OF_A_KIND(Category):
    def check(dice):
        return dice[0] == dice[3] or dice[1] == dice[4]

    def score(dice):
        return dice[1] * 4


class LITTLE_STRAIGHT(Category):
    def check(dice):
        return all(map(lambda x: x in dice, range(1, 6)))

    def score(dice):
        return 30


class BIG_STRAIGHT(Category):
    def check(dice):
        return all(map(lambda x: x in dice, range(2, 7)))

    def score(dice):
        return 30


class CHOICE(Category):
    def check(dice):
        return True

    def score(dice):
        return sum(dice)


class YACHT(Category):
    def check(dice):
        return dice[0] == dice[4]

    def score(dice):
        return 50


def score(dice, category):
    return category.score(dice) if category.check(sorted(dice)) else 0