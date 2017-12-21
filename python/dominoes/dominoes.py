import itertools


def chain(dominoes):
    if not dominoes:
        return []

    for permutation in itertools.permutations(dominoes):
        solution = check(permutation)
        if solution is not None:
            return solution
    return None


def check(permutation):
    last_prev, prev = permutation[0]
    solution = [permutation[0]]
    for i in range(1, len(permutation)):
        if permutation[i][0] == prev:
            prev = permutation[i][1]
            solution.append(permutation[i])
        elif permutation[i][1] == prev:
            prev = permutation[i][0]
            solution.append(permutation[i][::-1])
        else:
            return None
    return solution if prev == last_prev else None
