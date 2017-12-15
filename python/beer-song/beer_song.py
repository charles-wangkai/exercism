def verse(number):
    if number == 0:
        return ('No more bottles of beer on the wall, no more bottles of beer.\n'
                'Go to the store and buy some more, 99 bottles of beer on the wall.\n')
    elif number == 1:
        return ('1 bottle of beer on the wall, 1 bottle of beer.\n'
                'Take it down and pass it around, no more bottles of beer on the wall.\n')
    else:
        return ('{} bottles of beer on the wall, {} bottles of beer.\n'
                'Take one down and pass it around, {} bottle{} of beer on the wall.\n').format(
            number, number, number - 1, '' if number == 2 else 's')


def song(number1, number2=0):
    return ''.join(map(lambda n: verse(n) + '\n', range(number1, number2 - 1, -1)))
