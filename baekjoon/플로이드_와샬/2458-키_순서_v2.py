# 1808ms
import sys
INPUT = sys.stdin.readline

n, m = map(int, INPUT().split())
graph = [[False] * n for _ in range(n)]
for _ in range(m):
    a, b = map(int, INPUT().split())
    graph[a-1][b-1] = True

for k in range(n):
    for y in range(n):
        for x in range(n):
            if not graph[y][x]:
                graph[y][x] = graph[y][k] & graph[k][x]

ans = 0
for y in range(n):
    count = 1
    for x in range(n):
        if graph[y][x]: count += 1
        if graph[x][y]: count += 1
    if count == n:
        ans += 1

print(ans)