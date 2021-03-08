# 7268ms

import sys

input = sys.stdin.readline

r, c = map(int, input().rsplit())
board = [list(input()) for _ in range(r)]
dir = [[-1, 0], [0, 1], [1, 0], [0, -1]]
visit = [False] * 26


def getOrd(alpha):
    return ord(alpha) - ord('A')

def dfs(y, x, count):
    ret = count
    for dy, dx in dir:
        ny = y + dy
        nx = x + dx

        if 0 <= ny < r and 0 <= nx < c:
            this_ord = getOrd(board[ny][nx])
            
            if not visit[this_ord]:
                visit[this_ord] = True
                ret = max(ret, dfs(ny, nx, count + 1))
                visit[this_ord] = False

    return ret

visit[getOrd(board[0][0])] = True
print(dfs(0, 0, 1))