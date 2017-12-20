SHARPS = ['A', 'A#', 'B', 'C', 'C#', 'D', 'D#', 'E', 'F', 'F#', 'G', 'G#']
FLATS = ['A', 'Bb', 'B', 'C', 'Db', 'D', 'Eb', 'E', 'F', 'Gb', 'G', 'Ab']

FLAT_TONICS = {'F', 'Bb', 'Eb', 'Ab', 'Db',
               'Gb', 'd', 'g', 'c', 'f', 'bb', 'eb'}

INTERVAL_2_OFFSET = {'m': 1, 'M': 2, 'A': 3}


class Scale(object):
    def __init__(self, tonic, scale_name, pattern=None):
        self.name = '{} {}'.format(tonic.capitalize(), scale_name)

        scale = FLATS if tonic in FLAT_TONICS else SHARPS

        start_index = scale.index(tonic.capitalize())
        if pattern is None:
            self.pitches = scale[start_index:] + scale[:start_index]
        else:
            self.pitches = []
            i = start_index
            for interval in pattern:
                self.pitches.append(scale[i])

                i = (i + INTERVAL_2_OFFSET[interval]) % len(scale)

            if i != start_index:
                raise ValueError('Broken interval')
