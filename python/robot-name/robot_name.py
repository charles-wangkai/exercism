import random
import string


class Robot(object):
    names = set()

    def __init__(self):
        while True:
            self.name = '{0}{1}{2}{3}{4}'.format(self.generate_letter(), self.generate_letter(),
                                                 self.generate_digit(), self.generate_digit(), self.generate_digit())
            if self.name not in self.names:
                self.names.add(self.name)
                break

    def reset(self):
        self.__init__()

    @classmethod
    def generate_letter(cls):
        return random.choice(string.ascii_uppercase)

    @classmethod
    def generate_digit(cls):
        return random.randrange(10)
