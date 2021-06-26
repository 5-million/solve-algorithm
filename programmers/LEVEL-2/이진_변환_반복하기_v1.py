def get_trasnform_len(s):
    length = 0
    for ch in s:
        if ch == '1':
            length += 1
    
    return length

def solution(s):
    removed_zero_count = 0
    transform_count = 0

    while s != '1':
        s_len = len(s)
        next_s_len = get_trasnform_len(s)

        removed_zero_count += s_len - next_s_len
        transform_count += 1

        s = format(next_s_len, 'b')

    return [transform_count, removed_zero_count]

print('result: [3, 8]', solution("110010101001"))
print('result: [3, 3]', solution("01110"))
print('result: [4, 1]', solution("1111111"))