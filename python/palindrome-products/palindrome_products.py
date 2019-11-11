def largest(min_factor, max_factor):
    product_to_factorizations = compute_product_to_factorizations(
        min_factor, max_factor)

    if not product_to_factorizations:
        return (None, [])

    return (max(product_to_factorizations.keys()), product_to_factorizations[max(product_to_factorizations.keys())])


def smallest(min_factor, max_factor):
    product_to_factorizations = compute_product_to_factorizations(
        min_factor, max_factor)

    if not product_to_factorizations:
        return (None, [])

    return (min(product_to_factorizations.keys()), product_to_factorizations[min(product_to_factorizations.keys())])


def compute_product_to_factorizations(min_factor, max_factor):
    if min_factor > max_factor:
        raise ValueError('Min factor is greater than max factor!')

    product_to_factorizations = {}
    for i in range(min_factor, max_factor + 1):
        for j in range(i, max_factor + 1):
            product = i * j
            if str(product) == str(product)[::-1]:
                if product not in product_to_factorizations:
                    product_to_factorizations[product] = []
                product_to_factorizations[product].append([i, j])
    return product_to_factorizations
