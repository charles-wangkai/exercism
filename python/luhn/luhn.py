class Luhn(object):

    def __init__(self, candidate):
        self.candidate = candidate

    def is_valid(self):
        candidate = self.candidate.replace(' ', '')
        if len(candidate) <= 1 or not all(map(str.isdigit, candidate)):
            return False

        return sum(map(lambda i: self.do_double(int(candidate[i])) if (len(candidate) - i) % 2 == 0 else int(candidate[i]), range(0, len(candidate)))) % 10 == 0
    
    def do_double(self, x):
        result = x * 2
        if result > 9:
            result -= 9
        return result
