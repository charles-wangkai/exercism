def best_hands(hands):
    return [h for h in hands if Category(h) == max(map(Category, hands), key=lambda c: (c.rank, c.candidates))]


class Category:
    def __init__(self, h):
        cards = sorted(map(Card, h.split()),
                       key=lambda card: card.value, reverse=True)

        if self.is_straight_flush(cards):
            self.rank = -1

            if self.is_ace_to_five(cards):
                self.candidates = [5]
            else:
                self.candidates = [cards[0].value]
        elif self.is_square(cards):
            self.rank = -2

            if cards[0].value == cards[3].value:
                self.candidates = [cards[0].value, cards[4].value]
            else:
                self.candidates = [cards[4].value, cards[0].value]
        elif self.is_full(cards):
            self.rank = -3

            if cards[0].value == cards[2].value and cards[3].value == cards[4].value:
                self.candidates = [cards[0].value, cards[3].value]
            else:
                self.candidates = [cards[2].value, cards[0].value]
        elif self.is_flush(cards):
            self.rank = -4

            self.candidates = [cards[0].value, cards[1].value,
                               cards[2].value, cards[3].value, cards[4].value]
        elif self.is_straight(cards):
            self.rank = -5

            if self.is_ace_to_five(cards):
                self.candidates = [5]
            else:
                self.candidates = [cards[0].value]
        elif self.is_three(cards):
            self.rank = -6

            if cards[0].value == cards[2].value:
                self.candidates = [cards[0].value,
                                   cards[3].value, cards[4].value]
            elif cards[1].value == cards[3].value:
                self.candidates = [cards[1].value,
                                   cards[0].value, cards[4].value]
            else:
                self.candidates = [cards[2].value,
                                   cards[0].value, cards[1].value]
        elif self.is_double_pair(cards):
            self.rank = -7

            if cards[0].value == cards[1].value and cards[2].value == cards[3].value:
                self.candidates = [cards[0].value,
                                   cards[2].value, cards[4].value]
            elif cards[0].value == cards[1].value and cards[3].value == cards[4].value:
                self.candidates = [cards[0].value,
                                   cards[3].value, cards[2].value]
            else:
                self.candidates = [cards[1].value,
                                   cards[3].value, cards[0].value]
        elif self.is_one_pair(cards):
            self.rank = -8

            if cards[0].value == cards[1].value:
                self.candidates = [
                    cards[0].value, cards[2].value, cards[3].value, cards[4].value]
            elif cards[1].value == cards[2].value:
                self.candidates = [
                    cards[1].value, cards[0].value, cards[3].value, cards[4].value]
            elif cards[2].value == cards[3].value:
                self.candidates = [
                    cards[2].value, cards[0].value, cards[1].value, cards[4].value]
            else:
                self.candidates = [
                    cards[3].value, cards[0].value, cards[1].value, cards[2].value]
        else:
            self.rank = -9

            self.candidates = [cards[0].value, cards[1].value,
                               cards[2].value, cards[3].value, cards[4].value]

    def __eq__(self, other):
        return self.rank == other.rank and self.candidates == other.candidates

    def is_full(self, cards):
        return (cards[0].value == cards[2].value and cards[3].value == cards[4].value) or (cards[0].value == cards[1].value and cards[2].value == cards[4].value)

    def is_three(self, cards):
        return cards[0].value == cards[2].value or cards[1].value == cards[3].value or cards[2].value == cards[4].value

    def is_ace_to_five(self, cards):
        return cards[0].value == 14 and cards[1].value == 5 and cards[2].value == 4 and cards[3].value == 3 and cards[4].value == 2

    def is_straight(self, cards):
        return all(map(lambda i: cards[i].value - cards[i + 1].value == 1, range(0, 4))) or self.is_ace_to_five(cards)

    def is_square(self, cards):
        return cards[0].value == cards[3].value or cards[1].value == cards[4].value

    def is_flush(self, cards):
        return len(set(map(lambda card: card.suit, cards))) == 1

    def is_straight_flush(self, cards):
        return self.is_straight(cards) and self.is_flush(cards)

    def is_one_pair(self, cards):
        return any(map(lambda i: cards[i].value == cards[i + 1].value, range(0, 4)))

    def is_double_pair(self, cards):
        return (cards[0].value == cards[1].value and cards[2].value == cards[3].value) or (cards[0].value == cards[1].value and cards[3].value == cards[4].value) or (cards[1].value == cards[2].value and cards[3].value == cards[4].value)


class Card:
    def __init__(self, s):
        self.suit = s[-1:]
        self.value = [None, None, '2', '3', '4', '5', '6',
                      '7', '8', '9', '10', 'J', 'Q', 'K', 'A'].index(s[:-1])
