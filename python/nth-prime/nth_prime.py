def prime(positive_number):
    if positive_number <= 0:
        raise ValueError('Invalid input!')

    prime = 1
    for _ in range(positive_number):
        prime = next_prime(prime)
    return prime


def next_prime(x):
    while True:
        x += 1
        if is_prime(x):
            return x


def is_prime(x):
    i = 2
    while i * i <= x:
        if x % i == 0:
            return False
        i += 1
    return True
