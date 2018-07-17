def hey(phrase):
    if not phrase or phrase.isspace():
        return 'Fine. Be that way!'

    phrase_is_question = phrase.rstrip().endswith('?')

    content = ''.join([ch for ch in phrase if ch.isalpha()])
    if content.isupper():
        if phrase_is_question:
            return 'Calm down, I know what I\'m doing!'
        else:
            return 'Whoa, chill out!'

    if phrase_is_question:
        return 'Sure.'

    return 'Whatever.'
