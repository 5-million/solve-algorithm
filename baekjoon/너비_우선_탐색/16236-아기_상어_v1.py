# 160ms

import sys
from collections import deque

input = sys.stdin.readline

n = int(input())

space = []
shark_pos= []
for i in range(n):
    space.append(list(map(int, input().rsplit())))
    if 9 in space[i]:
        shark_pos = [i, space[i].index(9)]
        space[i][shark_pos[1]] = 0

shark = 2
def bfs(y, x):
    dir = [[-1, 0], [0, -1], [0, 1], [1, 0]]
    q = deque()
    q.append([y, x])

    visit = [[False] * n for _ in range(n)]
    visit[y][x] = True

    time = -1
    while q:
        q_len = len(q)
        time += 1
        temp = []
        for _ in range(q_len):
            temp.append(q.popleft())
        temp.sort(key = lambda x: (x[0], x[1]))

        for y, x in temp:
            if 0 < space[y][x] < shark:
                return [y, x, time]

            for dy, dx in dir:
                ny = y + dy
                nx = x + dx

                if 0 <= ny < n and 0 <= nx < n and space[ny][nx] <= shark and not visit[ny][nx]:
                    visit[ny][nx] = True
                    q.append([ny, nx])
    
    return False

ans, eat = 0, 0
while True:
    result = bfs(shark_pos[0], shark_pos[1])
    if result:
        y, x, time = result
        ans += time
        shark_pos = [y, x]
        space[y][x] = 0
        eat += 1

        if eat == shark:
            eat = 0
            shark += 1
    else:
        break

print(ans)