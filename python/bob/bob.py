def hey(phrase):
    if all(map(str.isspace, phrase)):
        return 'Fine. Be that way!'
    
    content = list(filter(str.isalpha, phrase))
    if len(content) > 0 and all(map(str.isupper, content)):
        return 'Whoa, chill out!'
    
    if phrase.rstrip().endswith('?'):
        return 'Sure.'
    
    return 'Whatever.'