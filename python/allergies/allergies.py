class Allergies(object):
    ITEM_TO_SCORE = {
        'eggs': 1,
        'peanuts': 2,
        'shellfish': 4,
        'strawberries': 8,
        'tomatoes': 16,
        'chocolate': 32,
        'pollen': 64,
        'cats': 128
    }

    def __init__(self, score):
        self.score = score

    def allergic_to(self, item):
        return (self.score & self.ITEM_TO_SCORE.get(item, 0)) != 0

    @property
    def lst(self):
        return list(filter(self.allergic_to, self.ITEM_TO_SCORE))
