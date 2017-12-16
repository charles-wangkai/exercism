def largest_palindrome(max_factor, min_factor=0):
    product2factorizations = compute_product2factorizations(
        max_factor, min_factor)
    return (max(product2factorizations.keys()), product2factorizations[max(product2factorizations.keys())])


def smallest_palindrome(max_factor, min_factor=0):
    product2factorizations = compute_product2factorizations(
        max_factor, min_factor)
    return (min(product2factorizations.keys()), product2factorizations[min(product2factorizations.keys())])


def compute_product2factorizations(max_factor, min_factor):
    product2factorizations = {}
    for i in range(min_factor, max_factor + 1):
        for j in range(min_factor, max_factor + 1):
            product = i * j
            if str(product) == str(product)[::-1]:
                product2factorizations[product] = {i, j}
    return product2factorizations
