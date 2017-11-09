class Allergies(object):
    ITEM2SCORE = {'eggs': 1, 'peanuts': 2, 'shellfish': 4, 'strawberries': 8, 'tomatoes': 16, 'chocolate': 32, 'pollen': 64, 'cats': 128}

    def __init__(self, score):
        self.score = score

    def is_allergic_to(self, item):
        return self.score & self.ITEM2SCORE[item] != 0

    @property
    def lst(self):
        return list(filter(self.is_allergic_to, self.ITEM2SCORE.keys()))
