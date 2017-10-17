def encode(string):
    result = ''
    prev, count = None, -1
    for i in range(len(string) + 1):
        if i < len(string) and string[i] == prev:
            count += 1
        else:
            if count > 0:
                if count != 1:
                    result += str(count)
                result += prev
            
            if i < len(string):
                prev, count = string[i], 1
    return result

def decode(string):
    result = ''
    index = 0
    while index < len(string):
        end_index = index
        while end_index < len(string) and string[end_index].isdigit():
            end_index += 1
            
        count = None
        if end_index == index:
            count = 1
        else:
            count = int(string[index:end_index])
        
        for i in range(count):
            result += string[end_index]
        
        index = end_index + 1
    return result
