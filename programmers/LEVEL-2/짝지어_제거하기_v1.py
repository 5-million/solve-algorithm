from typing import Collection


def solution(s):
    temp = []
    for char in s:
        if temp and char == temp[-1]:
            temp.pop()
        else:
            temp.append(char)

    if temp: return 0
    else: return 1

print('answer: 1', solution('baabaa'))
print('answer: 0', solution('cdcd'))