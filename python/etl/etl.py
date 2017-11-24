def transform(legacy_data):
    letter2score = {}
    for score, letters in legacy_data.items():
        for letter in letters:
            letter2score[letter.lower()] = score
    return letter2score