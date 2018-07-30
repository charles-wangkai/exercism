def sum_of_multiples(limit, multiples):
    return sum({i
                for multiple in multiples
                for i in range(multiple, limit, multiple)})
