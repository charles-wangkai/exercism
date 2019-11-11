def sum_of_multiples(limit, multiples):
    return sum({
        i
        for multiple in multiples
        if multiple
        for i in range(multiple, limit, multiple)
    })
