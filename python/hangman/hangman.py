# Game status categories
# Change the values as you see fit
STATUS_WIN = "win"
STATUS_LOSE = "lose"
STATUS_ONGOING = "ongoing"


class Hangman:
    def __init__(self, word):
        self.remaining_guesses = 9
        self.status = STATUS_ONGOING
        self.word = word
        self.masked_letters = set(word)

    def guess(self, char):
        if self.status != STATUS_ONGOING:
            raise ValueError('Game is over already!')

        if char in self.masked_letters:
            self.masked_letters.remove(char)

            if not self.masked_letters:
                self.status = STATUS_WIN
        else:
            if self.remaining_guesses == 0:
                self.status = STATUS_LOSE
            else:
                self.remaining_guesses -= 1

    def get_masked_word(self):
        return ''.join('_' if letter in self.masked_letters else letter for letter in self.word)

    def get_status(self):
        return self.status
