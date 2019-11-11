def is_pangram(s):
    return len(set(filter(str.isalpha, s.lower()))) == 26