def flatten(iterable):
    result = []
    search(result, iterable)
    return result


def search(result, iterable):
    for element in iterable:
        if element is not None:
            if isinstance(element, (list, tuple)):
                search(result, element)
            else:
                result.append(element)
