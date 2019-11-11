class Luhn:
    def __init__(self, candidate):
        self.candidate = candidate.replace(' ', '')

    def valid(self):
        if len(self.candidate) <= 1 or not self.candidate.isdigit():
            return False

        return sum(
            self.convert(len(self.candidate) - 1 - i, int(self.candidate[i]))
            for i in range(len(self.candidate))
        ) % 10 == 0

    def convert(self, right_index, value):
        return self.double(value) if right_index % 2 != 0 else value

    def double(self, x):
        result = x * 2
        if result > 9:
            result -= 9
        return result
