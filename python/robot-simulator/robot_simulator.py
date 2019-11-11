# Globals for the directions
# Change the values as you see fit
EAST = {'offset': (1, 0)}
NORTH = {'offset': (0, 1)}
WEST = {'offset': (-1, 0)}
SOUTH = {'offset': (0, -1)}


class Robot(object):
    DIRECTIONS = [EAST, NORTH, WEST, SOUTH]

    def __init__(self, direction=NORTH, x=0, y=0):
        self.instruction_to_command = {
            'R': self.turn_right, 'L': self.turn_left, 'A': self.advance}

        self.direction = direction
        self.coordinates = (x, y)

    def advance(self):
        self.coordinates = tuple(
            map(sum, zip(self.coordinates, self.direction['offset'])))

    def turn_left(self):
        self.direction = self.DIRECTIONS[(self.DIRECTIONS.index(
            self.direction) + 1) % len(self.DIRECTIONS)]

    def turn_right(self):
        self.direction = self.DIRECTIONS[(self.DIRECTIONS.index(
            self.direction) - 1) % len(self.DIRECTIONS)]

    def move(self, instructions):
        for instruction in instructions:
            self.instruction_to_command[instruction]()
