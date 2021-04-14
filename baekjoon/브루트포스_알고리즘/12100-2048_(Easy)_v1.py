# 704ms
import copy

n = int(input())
board = [list(map(int, input().rsplit())) for _ in range(n)]
DIRECTION = ['U', 'D', 'R', 'L']

def remove_all_empty(li):
    return list(filter(lambda x: x > 0, li))

def merge(li: list):
    li = remove_all_empty(li)
    if not li: return [0] * n

    temp = [(li[0], False)]
    for elem in li[1:]:
        if elem == 0: continue

        num, isMerge = temp.pop()
        if isMerge:
            temp += [(num, isMerge), (elem, False)]
        else:
            if num == elem:
                temp.append((elem*2, True))
            else:
                temp += [(num, isMerge), (elem, False)]

    temp = [num for num, _ in temp]
    return temp + [0] * (n - len(temp))

def merge_all(li: list):
    return [merge(l) for l in li]

def transform_up_and_down(direction, board):
    temp = []
    for x in range(n):
        temp_li = []
        for y in range(n):
            temp_li.append(board[y][x])
        
        if direction == 'D':
            temp_li.reverse()
        
        temp.append(temp_li)
    
    return temp

def reverse_transform_down(board):
    for brd in board:
        brd.reverse()

    temp = []
    for x in range(n):
        temp_li = []
        for y in range(n):
            temp_li.append(board[y][x])

        temp.append(temp_li)
    
    return temp

def transform_right_and_left(direction, board):
    temp = copy.deepcopy(board)
    if direction == 'L':
        return board

    for tmp in temp:
        tmp.reverse()

    return temp    

def transform(direction, board):
    if direction == 'U' or direction == 'D':
        return transform_up_and_down(direction, board)
    else:
        return transform_right_and_left(direction, board)

def reverse_transform(direction, board):
    if direction == 'U':
        return transform_up_and_down(direction, board)
    elif direction == 'D':
        return reverse_transform_down(board)
    else:
        return transform_right_and_left(direction, board)

def find_max_value(board):
    max_ = 0
    for brd in board:
        max_ = max(max_, max(brd))
    
    return max_

def executeMove(direction, board: list):
    temp = copy.deepcopy(transform(direction, board))
    temp = copy.deepcopy(merge_all(temp))
    return reverse_transform(direction, temp)

max_ = 0
def dfs(count, board):
    global max_

    if count == 5:
        return

    for di in DIRECTION:
        temp = copy.deepcopy(executeMove(di, board))
        max_ = max(max_, find_max_value(temp))
        dfs(count+1, temp)

dfs(0, board)
print(max_)