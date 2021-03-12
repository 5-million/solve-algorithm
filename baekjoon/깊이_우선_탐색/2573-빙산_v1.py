# 2084ms

import sys
import copy
from collections import deque

input = sys.stdin.readline

n, m = map(int, input().rsplit())
arctic = [list(map(int, input().rsplit())) for _ in range(n)]
visit = [[False] * m for _ in range(n)]
DIR = [[-1, 0], [0, 1], [1, 0], [0, -1]]

def bfs(y, x):
    q = deque()
    q.append([y, x])
    visit[y][x] = True

    while q:
        y, x = q.popleft()
        for dy, dx in DIR:
            ny = y + dy
            nx = x + dx

            if 0 <= ny < n and 0 <= nx < m and arctic[ny][nx] and not visit[ny][nx]:
                visit[ny][nx] = True
                q.append([ny, nx])
    
    return 1

def getLumpCount():
    global visit
    visit = [[False] * m for _ in range(n)]

    count = 0
    for y in range(n):
        for x in range(m):
            if arctic[y][x] and not visit[y][x]:
                count += bfs(y, x)
    
    return count

def melt():
    after_arctic = copy.deepcopy(arctic)
    for y in range(n):
        for x in range(m):
            if not arctic[y][x]: continue
            for dy, dx in DIR:
                ny = y + dy
                nx = x + dx

                if 0 <= ny < n and 0 <= nx < m and not arctic[ny][nx]:
                    if after_arctic[y][x]: after_arctic[y][x] -= 1
    
    return after_arctic

def isMeltedAll():
    if sum([sum(col) for col in arctic]): return False
    else: return True

ans = 0
flag = False
while not isMeltedAll():
    ans += 1
    arctic = melt()

    if getLumpCount() > 1:
        flag = True
        break

print(ans) if flag else print(0)