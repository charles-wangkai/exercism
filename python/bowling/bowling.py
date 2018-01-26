OPEN, SPARE, STRIKE = range(3)


class Frame:
    def __init__(self, roll1, roll2):
        self.roll1 = roll1
        self.roll2 = roll2

    def get_score(self):
        return self.roll1 + self.roll2

    def get_frame_type(self):
        if self.roll1 == 10:
            return STRIKE
        elif self.get_score() == 10:
            return SPARE
        else:
            return OPEN


class StopException(Exception):
    pass


class BowlingGame(object):
    def __init__(self):
        self.rolls = []

    def roll(self, pins):
        self.rolls.append(pins)

        self.score(False)

    def score(self, final=True):
        try:
            frames = []
            self.roll_index = 0
            for i in range(10):
                frames.append(self.read_frame(final))

            frame9_type = frames[9].get_frame_type()
            if frame9_type == SPARE:
                roll1 = self.read_roll(final)

                frames.append(Frame(roll1, 0))
            elif frame9_type == STRIKE:
                roll1 = self.read_roll(final)
                roll2 = self.read_roll(final)

                if roll1 != 10 and roll1 + roll2 > 10:
                    raise ValueError

                frames.append(Frame(roll1, roll2))

            if self.roll_index < len(self.rolls):
                raise IndexError

            result = 0
            for i in range(9):
                frame = frames[i]
                result += frame.get_score()

                if frame.get_frame_type() == SPARE:
                    result += frames[i + 1].roll1
                elif frame.get_frame_type() == STRIKE:
                    next_roll = frames[i + 1].roll1

                    if next_roll == 10:
                        next_next_roll = frames[i + 2].roll1
                    else:
                        next_next_roll = frames[i + 1].roll2

                    result += next_roll + next_next_roll
            result += sum(map(lambda i: frames[i].get_score(),
                              range(9, len(frames))))
            return result
        except StopException:
            return

    def read_roll(self, final):
        if not final:
            if self.roll_index == len(self.rolls):
                raise StopException

        roll = self.rolls[self.roll_index]
        self.roll_index += 1

        if roll < 0 or roll > 10:
            raise ValueError

        return roll

    def read_frame(self, final):
        roll1 = self.read_roll(final)
        if roll1 == 10:
            return Frame(10, 0)

        roll2 = self.read_roll(final)
        if roll1 + roll2 > 10:
            raise ValueError

        return Frame(roll1, roll2)
