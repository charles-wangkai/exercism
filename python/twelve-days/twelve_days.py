SEQUENCES = [None, 'first', 'second', 'third', 'fourth', 'fifth',
             'sixth', 'seventh', 'eighth', 'ninth', 'tenth', 'eleventh', 'twelfth']
GIFTS = [None, 'a Partridge in a Pear Tree', 'two Turtle Doves', 'three French Hens', 'four Calling Birds', 'five Gold Rings', 'six Geese-a-Laying',
         'seven Swans-a-Swimming', 'eight Maids-a-Milking', 'nine Ladies Dancing', 'ten Lords-a-Leaping', 'eleven Pipers Piping', 'twelve Drummers Drumming']


def verse(day_number):
    result = 'On the {} day of Christmas my true love gave to me, '.format(
        SEQUENCES[day_number])
    for i in range(day_number, 0, -1):
        if i < day_number:
            result += ', '
        if day_number > 1 and i == 1:
            result += 'and '
        result += GIFTS[i]
    result += '.\n'
    return result


def verses(start, end):
    return ''.join(map(lambda n: verse(n) + '\n', range(start, end + 1)))


def sing():
    return verses(1, 12)
