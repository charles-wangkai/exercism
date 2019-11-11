import itertools

COLORS = [
    RED,
    GREEN,
    IVORY,
    YELLOW,
    BLUE
] = range(5)
NATIONALITIES = [
    ENGLISHMAN,
    SPANIARD,
    UKRAINIAN,
    NORWEGIAN,
    JAPANESE
] = range(5)
DRINKS = [
    COFFEE,
    TEA,
    MILK,
    ORANGE_JUICE,
    WATER
] = range(5)
SMOKES = [
    OLD_GOLD,
    KOOLS,
    CHESTERFIELDS,
    LUCKY_STRIKE,
    PARLIAMENTS
] = range(5)
PETS = [
    DOG,
    SNAILS,
    FOX,
    HORSE,
    ZEBRA
] = range(5)

NATIONALITY_NAMES = [
    'Englishman',
    'Spaniard',
    'Ukrainian',
    'Norwegian',
    'Japanese'
]


def drinks_water():
    return NATIONALITY_NAMES[n[d.index(DRINKS[WATER])]]


def owns_zebra():
    return NATIONALITY_NAMES[n[p.index(PETS[ZEBRA])]]


def solve():
    return next(filter(check, itertools.product(
        filter(lambda c: c[1] == COLORS[BLUE],
               itertools.permutations(COLORS)),
        filter(lambda n: n[0] == NATIONALITIES[NORWEGIAN],
               itertools.permutations(NATIONALITIES)),
        filter(lambda d: d[2] == DRINKS[MILK],
               itertools.permutations(DRINKS)),
        itertools.permutations(SMOKES),
        itertools.permutations(PETS)
    )))


def check(sol):
    c, n, d, s, p = sol
    return (
        n.index(NATIONALITIES[ENGLISHMAN]) == c.index(COLORS[RED])
        and n.index(NATIONALITIES[SPANIARD]) == p.index(PETS[DOG])
        and d.index(DRINKS[COFFEE]) == c.index(COLORS[GREEN])
        and n.index(NATIONALITIES[UKRAINIAN]) == d.index(DRINKS[TEA])
        and c.index(COLORS[GREEN]) == c.index(COLORS[IVORY]) + 1
        and s.index(SMOKES[OLD_GOLD]) == p.index(PETS[SNAILS])
        and s.index(SMOKES[KOOLS]) == c.index(COLORS[YELLOW])
        and abs(s.index(SMOKES[CHESTERFIELDS]) - p.index(PETS[FOX])) == 1
        and abs(s.index(SMOKES[KOOLS]) - p.index(PETS[HORSE])) == 1
        and s.index(SMOKES[LUCKY_STRIKE]) == d.index(DRINKS[ORANGE_JUICE])
        and n.index(NATIONALITIES[JAPANESE]) == s.index(SMOKES[PARLIAMENTS])
    )


c, n, d, s, p = solve()
