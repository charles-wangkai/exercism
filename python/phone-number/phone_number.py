class Phone(object):
    def __init__(self, phone_number):
        if not all(map(lambda ch: ch.isdigit() or ch in ' ()-.+', phone_number)):
            raise ValueError

        digits = ''.join(filter(str.isdigit, phone_number))
        length = len(digits)
        if length not in [10, 11]:
            raise ValueError

        if length == 11 and digits[0] != '1':
            raise ValueError

        self.number = digits[-10:]
        if not ('2' <= self.number[0] <= '9' and '2' <= self.number[3] <= '9'):
            raise ValueError

        self.area_code = self.number[:3]

    def pretty(self):
        return '({}) {}-{}'.format(self.area_code, self.number[3:6], self.number[6:])
