def translate(text):
    return ' '.join(map(translate_word, text.split()))


def translate_word(word):
    if starts_with_vowel(word):
        return word + 'ay'

    first_consonant = get_first_consonant(word)
    return word[len(first_consonant):] + first_consonant + 'ay'


def starts_with_vowel(word):
    return any([word.startswith(vowel) for vowel in ['yt', 'xr', 'a', 'e', 'i', 'o', 'u']])


def get_first_consonant(word):
    for multi_consonant in ['thr', 'sch', 'ch', 'qu', 'th', 'rh']:
        if word.startswith(multi_consonant):
            return multi_consonant

    if word[1:].startswith('qu'):
        return word[:3]

    return word[0]
