from collections import deque

INF = float('inf')
EMPTY = 0
WALL = 1
STRAIGHT = 100
CORNOR = 500

def solution(board):
    return min(bfs(board, 1), bfs(board, 2))

def bfs(board, start_d):
    n = len(board)
    direction = [[-1, 0], [0, 1], [1, 0], [0, -1]] # 상,하 = 짝수 인덱스/ 좌,우 = 홀수 인덱스

    temp = [[INF] * n for _ in range(n)]
    que = deque()

    temp[0][0] = 0
    que.append([0, 0, start_d, 0])

    while que:
        y, x, d, c = que.popleft()

        for idx, [dy, dx] in enumerate(direction):
            cost = STRAIGHT
            if d != idx: cost += CORNOR

            ny = y + dy
            nx = x + dx

            if 0 <= ny < n and 0 <= nx < n and board[ny][nx] == EMPTY and temp[ny][nx] > c + cost:
                temp[ny][nx] = c + cost
                que.append([ny, nx, idx, temp[ny][nx]])

    return temp[n-1][n-1]

print('result: 900', solution([[0, 0, 0], [0, 0, 0], [0, 0, 0]]))
print('result: 3800', solution([[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0],[0,0,0,0,0,1,0,0],[0,0,0,0,1,0,0,0],[0,0,0,1,0,0,0,1],[0,0,1,0,0,0,1,0],[0,1,0,0,0,1,0,0],[1,0,0,0,0,0,0,0]]))
print('result: 2100', solution([[0,0,1,0],[0,0,0,0],[0,1,0,1],[1,0,0,0]]))
print('result: 3200', solution([[0,0,0,0,0,0],[0,1,1,1,1,0],[0,0,1,0,0,0],[1,0,0,1,0,1],[0,1,0,0,0,1],[0,0,0,0,0,0]]))
print('result: 3000', solution([[0, 0, 0, 0, 0], [0, 1, 1, 1, 0], [0, 0, 1, 0, 0], [1, 0, 0, 0, 1], [0, 1, 1, 0, 0]]))