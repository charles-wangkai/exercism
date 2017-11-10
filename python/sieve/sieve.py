def sieve(limit):
    primes = [True] * (limit + 1)
    for i in range(2, len(primes)):
        for j in range(i * i, len(primes), i):
            primes[j] = False
    return list(filter(lambda x: primes[x], range(2, len(primes))))
