SUBLIST = object()
SUPERLIST = object()
EQUAL = object()
UNEQUAL = object()


def sublist(first_list, second_list):
    if contains(second_list, first_list):
        if contains(first_list, second_list):
            return EQUAL
        else:
            return SUBLIST
    else:
        if contains(first_list, second_list):
            return SUPERLIST
        else:
            return UNEQUAL


def contains(whole, part):
    return any([whole[i:i + len(part)] == part for i in range(len(whole) - len(part) + 1)])
