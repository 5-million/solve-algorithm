# 228ms

import sys
from collections import deque

INPUT = sys.stdin.readline
INF = float('inf')
WAY = [[-1, 0], [0, 1], [1, 0], [0, -1]] # up right down left

t = 1
while True:
    n = int(INPUT())
    if not n: break

    map_ = [list(map(int, INPUT().rsplit())) for _ in range(n)]

    q = deque()
    dist = [[INF] * n for _ in range(n)]

    q.append([0, 0])
    dist[0][0] = map_[0][0]

    while q:
        y, x = q.popleft()

        for wy, wx in WAY:
            ny = y + wy
            nx = x + wx

            if 0 <= ny < n and 0 <= nx < n and dist[ny][nx] > dist[y][x] + map_[ny][nx]:
                dist[ny][nx] = dist[y][x] + map_[ny][nx]
                q.append([ny, nx])
        
    print("Problem {0}: {1}".format(t, dist[n-1][n-1]))
    t += 1
