def sum_of_multiples(limit, multiples):
    return sum(filter(lambda x: any([x % multiple == 0 for multiple in multiples]), range(1, limit)))
