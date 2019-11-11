def response(phrase):
    if not phrase or phrase.isspace():
        return 'Fine. Be that way!'

    phrase_is_question = phrase.rstrip().endswith('?')

    if phrase.isupper():
        if phrase_is_question:
            return 'Calm down, I know what I\'m doing!'
        else:
            return 'Whoa, chill out!'

    if phrase_is_question:
        return 'Sure.'

    return 'Whatever.'
