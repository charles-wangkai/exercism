def detect_anagrams(word, candidates):
    return list(filter(lambda candidate: candidate.lower() != word.lower() and generate_key(candidate) == generate_key(word), candidates))


def generate_key(s):
    return sorted(s.lower())
