import copy

DELETED = 'DEL'
EMPTY = 'EMP'

def transform(m, n, board):
    temp = [[0] * m for _ in range(n)]
    for y in range(m):
        for x in range(n):
            temp[x][y] = board[y][x]
    
    for row in temp:
        row.reverse()
    
    return temp

def relocation(m, board):
    for row in board:
        while DELETED in row:
            row.remove(DELETED)
        row += [EMPTY] * (m-len(row))

def solution(m, n, board):
    board = copy.deepcopy(transform(m, n, board))
    temp = copy.deepcopy(board)

    square = [[0, 0], [0, 1], [1, 0], [1, 1]]

    while True:
        flag = False
        for y in range(n-1):
            for x in range(m-1):
                now = board[y][x]
                
                if now == DELETED or now == EMPTY:
                    continue

                for dy, dx in square:
                    if board[y+dy][x+dx] != now:
                        break
                else:
                    flag = True
                    for dy, dx in square:
                        temp[y+dy][x+dx] = DELETED

        relocation(m, temp)
        board = copy.deepcopy(temp)
        
        if not flag:
            break
    
    ans = 0
    for row in board:
        ans += row.count(EMPTY)
    
    return ans

print('answer: 14', solution(4, 5, ["CCBDE", "AAADE", "AAABF", "CCBBF"]))
print('answer: 15', solution(6, 6, ["TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"]))