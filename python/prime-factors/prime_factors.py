def factors(natural_number):
    result = []
    i = 2
    while i * i <= natural_number:
        if is_prime(i):
            while natural_number % i == 0:
                result.append(i)
                natural_number //= i

        i += 1
    if natural_number != 1:
        result.append(natural_number)
    return result


def is_prime(n):
    i = 2
    while i * i <= n:
        if n % i == 0:
            return False
        i += 1
    return True
