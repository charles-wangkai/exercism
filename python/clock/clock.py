class Clock(object):
    def __init__(self, hour, minute):
        self.total_minutes = (hour * 60 + minute) % (24 * 60)

    def __add__(self, other):
        return Clock(0, self.total_minutes + other)

    def __str__(self):
        return '{:0>2}:{:0>2}'.format(self.total_minutes // 60, self.total_minutes % 60)

    def __eq__(self, other):
        return self.total_minutes == other.total_minutes
