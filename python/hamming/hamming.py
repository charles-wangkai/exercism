def distance(strand_a, strand_b):
    if len(strand_a) != len(strand_b):
        raise ValueError
    
    return len(list(filter(lambda i: strand_a[i] != strand_b[i], range(0, len(strand_a)))))
