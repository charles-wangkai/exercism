def slices(series, length):
    if length < 1 or length > len(series):
        raise ValueError('Invalid length!')

    return [series[i: i + length] for i in range(0, len(series) - length + 1)]
