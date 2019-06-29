SEQUENCES = [
    'first',
    'second',
    'third',
    'fourth',
    'fifth',
    'sixth',
    'seventh',
    'eighth',
    'ninth',
    'tenth',
    'eleventh',
    'twelfth'
]
GIFTS = [
    'a Partridge in a Pear Tree',
    'two Turtle Doves',
    'three French Hens',
    'four Calling Birds',
    'five Gold Rings',
    'six Geese-a-Laying',
    'seven Swans-a-Swimming',
    'eight Maids-a-Milking',
    'nine Ladies Dancing',
    'ten Lords-a-Leaping',
    'eleven Pipers Piping',
    'twelve Drummers Drumming'
]


def verse(day_number):
    result = f'On the {SEQUENCES[day_number - 1]} day of Christmas my true love gave to me: '
    for i in range(day_number, 0, -1):
        if i < day_number:
            result += ', '
        if day_number > 1 and i == 1:
            result += 'and '
        result += GIFTS[i - 1]
    result += '.'
    return result


def recite(start, end):
    return [verse(n) for n in range(start, end + 1)]
