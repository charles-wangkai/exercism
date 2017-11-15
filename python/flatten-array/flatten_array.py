def flatten(iterable):

    def search(result, iterable):
        for element in iterable:
            if element is not None:
                if isinstance(element, list) or isinstance(element, tuple):
                    search(result, element)
                else:
                    result.append(element)
    
    result = []
    search(result, iterable)
    return result
