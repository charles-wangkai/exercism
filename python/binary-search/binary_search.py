def binary_search(list_of_numbers, number):
    lower_index, upper_index = 0, len(list_of_numbers) - 1
    while lower_index <= upper_index:
        middle_index = (lower_index + upper_index) // 2
        if list_of_numbers[middle_index] == number:
            return middle_index
        elif list_of_numbers[middle_index] < number:
            lower_index = middle_index + 1
        else:
            upper_index = middle_index - 1
    raise ValueError('Not found')