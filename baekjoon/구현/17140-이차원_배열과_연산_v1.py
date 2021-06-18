import copy
from collections import Counter

r, c, k = map(int, input().split())

arr = [list(map(int, input(). split())) for _ in range(3)]

def operation_r(): # 모든 행에 대해, 행의 개수 >= 열의 개수
    return excute(arr)

def excute(arr):
    temp = []
    max_len = 0
    for i, row in enumerate(arr):
        temp.append([])
        counter = sorted(Counter(row).items(), key = lambda x: (x[1], x[0]))
        
        for count in counter:
            if count[0] == 0: 
                continue

            temp[i] += [count[0], count[1]]
            max_len = max(max_len, len(temp[i]))
    
    for i in range(len(temp)):
        temp[i] += [0] * (max_len - len(temp[i]))
        
        if len(temp[i]) > 100:
            temp[i] = temp[i][:100]
    
    return temp

def operation_c(): # 모든 열에 대해, 행의 개수 < 열의 개수
    global arr
    arr = copy.deepcopy(xy_symmetry(arr))

    return xy_symmetry(excute(arr))

def xy_symmetry(arr):
    temp = [[] for _ in range(len(arr[0]))]
    for i in range(len(arr)):
        for j in range(len(arr[0])):
            temp[j].append(arr[i][j])
    
    return temp

def solution():
    global arr

    for time in range(0, 101):
        if len(arr) >= r and len(arr[0]) >= c and arr[r-1][c-1] == k:
            return time

        if len(arr) >= len(arr[0]): # 행의 개수 >= 열의 개수
            arr = copy.deepcopy(operation_r())
        else:
            arr = copy.deepcopy(operation_c())        
    
    return -1

print(solution())
