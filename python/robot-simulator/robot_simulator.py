# Globals for the bearings
# Change the values as you see fit
EAST = {'offset': (1, 0)}
NORTH = {'offset': (0, 1)}
WEST = {'offset': (-1, 0)}
SOUTH = {'offset': (0, -1)}


class Robot(object):
    BEARINGS = [EAST, NORTH, WEST, SOUTH]

    def __init__(self, bearing=NORTH, x=0, y=0):
        self.instruction2command = {'R': self.turn_right, 'L': self.turn_left, 'A': self.advance}
        
        self.bearing = bearing
        self.coordinates = (x, y)

    def advance(self):
        self.coordinates = tuple(map(sum, zip(self.coordinates, self.bearing['offset'])))
    
    def turn_left(self):
        self.bearing = self.BEARINGS[(self.BEARINGS.index(self.bearing) + 1) % len(self.BEARINGS)]
    
    def turn_right(self):
        self.bearing = self.BEARINGS[(self.BEARINGS.index(self.bearing) - 1) % len(self.BEARINGS)]

    def simulate(self, instructions):
        for instruction in instructions:
            self.instruction2command[instruction]()
