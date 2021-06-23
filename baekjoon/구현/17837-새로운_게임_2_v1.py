from enum import Enum

n, k = map(int, input().split())

board = []
for _ in range(n):
    row = list(map(int, input().split()))

    temp = []
    for elem in row:
        temp.append([elem, []])

    board.append(temp)

piece = [] # [[y, x, direction], ...]
for i in range(k):
    y, x, direction = map(int, input().split())

    y -= 1
    x -= 1
    direction -= 1

    board[y][x][1].append(i)
    piece.append([y, x, direction])

WHITE = 0
RED = 1
BLUE = 2

direction = [[0, 1], [0, -1], [-1, 0], [1, 0]] # rigtht left up down
reverse_direction = {0: 1, 1: 0, 2: 3, 3: 2}

def solution():
    for turn in range(1, 1001):
        if move_all():
            return turn
    
    return -1

def move_all():
    for id in range(k):
        move(id)
        
        y, x = piece[id][0], piece[id][1]
        if len(board[y][x][1]) >= 4:
            return True
        
    return False

def move(piece_id):
    y, x, d = piece[piece_id]

    index = get_piece_index(piece_id, y, x)
    nxt = get_next(y, x, d)
    nxt_color = get_next_color(nxt)

    if nxt_color == BLUE:
        d = reverse_direction[d]
        piece[piece_id][2] = d
        nxt = get_next(y, x, d)
        nxt_color = get_next_color(nxt)

    if nxt_color != BLUE:
        for id in board[y][x][1][index:]:
            piece[id][0] = nxt[0]
            piece[id][1] = nxt[1]

        update_board_state(index, [y, x], nxt, nxt_color)

def get_piece_index(target, y, x):
    for index, id in enumerate(board[y][x][1]):
        if id == target:
            return index

def get_next(y, x, d):
    ny = y + direction[d][0]
    nx = x + direction[d][1]

    return [ny, nx]

def get_next_color(nxt):
    if 0 <= nxt[0] < n and 0 <= nxt[1] < n:
        return board[nxt[0]][nxt[1]][0]
    else:
        return BLUE

def update_board_state(target_index, now, nxt, nxt_color):
    target = board[now[0]][now[1]][1][target_index:]
    board[now[0]][now[1]][1] = board[now[0]][now[1]][1][:target_index]

    if nxt_color == WHITE:
        white(nxt, target)

    if nxt_color == RED:
        red(nxt, target)

def white(nxt, target):
    board[nxt[0]][nxt[1]][1] += target

def red(nxt, target):
    board[nxt[0]][nxt[1]][1] += reversed(target)

print(solution())