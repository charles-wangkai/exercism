def say(number):
    CHUNK_NAMES = ['', ' thousand', ' million', ' billion']

    number = int(number)

    if number < 0 or number > 999999999999:
        raise ValueError('Invalid number!')

    if number == 0:
        return 'zero'

    chunks = [int(str(number)[::-1][i:i + 3][::-1])
              for i in range(0, len(str(number)), 3)]
    parts = list(map(lambda e: (say_within_thousand(
        e[0]) + e[1]), filter(lambda e: e[0] != 0, zip(chunks, CHUNK_NAMES))))[::-1]
    if 0 < number % 1000 < 100 and number >= 1000:
        parts.insert(-1, 'and')
    return ' '.join(parts)


def say_within_thousand(n):
    ONE_NAMES = [None, 'one', 'two', 'three', 'four',
                 'five', 'six', 'seven', 'eight', 'nine']
    TEN_NAMES = [None, 'ten', 'twenty', 'thirty', 'forty',
                 'fifty', 'sixty', 'seventy', 'eighty', 'ninety']
    TEEN_NAMES = ['eleven', 'twelve', 'thirteen', 'fourteen',
                  'fifteen', 'sixteen', 'seventeen', 'eighteen', 'nineteen']

    parts = []

    hundred = n // 100
    if hundred > 0:
        parts.append(ONE_NAMES[hundred] + ' hundred')

    n %= 100
    if n > 0:
        if parts:
            parts.append('and')

        if 11 <= n <= 19:
            parts.append(TEEN_NAMES[n - 11])
        else:
            part = ''

            ten = n // 10
            if ten > 0:
                part += TEN_NAMES[ten]

            one = n % 10
            if one > 0:
                if part:
                    part += '-'
                part += ONE_NAMES[one]

            if part:
                parts.append(part)

    return ' '.join(parts)
