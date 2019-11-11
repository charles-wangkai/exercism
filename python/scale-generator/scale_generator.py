SHARPS = ['A', 'A#', 'B', 'C', 'C#', 'D', 'D#', 'E', 'F', 'F#', 'G', 'G#']
FLATS = ['A', 'Bb', 'B', 'C', 'Db', 'D', 'Eb', 'E', 'F', 'Gb', 'G', 'Ab']

FLAT_TONICS = {'F', 'Bb', 'Eb', 'Ab', 'Db',
               'Gb', 'd', 'g', 'c', 'f', 'bb', 'eb'}

INTERVAL_TO_OFFSET = {'m': 1, 'M': 2, 'A': 3}


class Scale(object):
    def __init__(self, tonic, pattern=None):
        self.scale = FLATS if tonic in FLAT_TONICS else SHARPS
        self.start_index = self.scale.index(tonic.capitalize())

    def chromatic(self):
        return self.scale[self.start_index:] + self.scale[:self.start_index]

    def interval(self, pattern):
        result = []
        i = self.start_index
        for interval in pattern:
            result.append(self.scale[i])

            i = (i + INTERVAL_TO_OFFSET[interval]) % len(self.scale)

        return result
