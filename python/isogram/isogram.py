def is_isogram(string):
    letters = list(filter(str.isalpha, string.lower()))
    return len(letters) == len(set(letters))