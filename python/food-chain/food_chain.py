ANIMALS = [None, 'fly', 'spider', 'bird', 'cat', 'dog', 'goat', 'cow', 'horse']
COMMENTS = [None, None, 'It wriggled and jiggled and tickled inside her.', 'How absurd to swallow a bird!', 'Imagine that, to swallow a cat!',
            'What a hog, to swallow a dog!', 'Just opened her throat and swallowed a goat!', 'I don\'t know how she swallowed a cow!']
PARTS = [None, None, 'She swallowed the spider to catch the fly.', 'She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.',
         'She swallowed the cat to catch the bird.', 'She swallowed the dog to catch the cat.', 'She swallowed the goat to catch the dog.', 'She swallowed the cow to catch the goat.']


def chain():
    return '\n\n'.join(map(verse, range(1, 9)))


def verse(n):
    result = 'I know an old lady who swallowed a {}.'.format(ANIMALS[n])
    if n not in [1, 8]:
        result += '\n' + COMMENTS[n]
    if n != 8:
        for i in range(n, 1, -1):
            result += '\n' + PARTS[i]
    result += '\n' + ('She\'s dead, of course!' if n == 8
                      else 'I don\'t know why she swallowed the fly. Perhaps she\'ll die.')
    return result
