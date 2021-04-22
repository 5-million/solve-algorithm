# 3320ms
INF = float('inf')

n, m = map(int, input().split())
graph = [[INF] * n for _ in range(n)]
for _ in range(m):
    a, b = map(int, input().split())
    graph[a-1][b-1] = 1

for y in range(n):
    for x in range(n):
        if y == x: graph[y][x] = 0

for k in range(n):
    for y in range(n):
        for x in range(n):
            graph[y][x] = min(graph[y][x], graph[y][k] + graph[k][x])

ans = 0
for y in range(n):
    count = -1
    for x in range(n):
        if graph[y][x] != INF: count += 1
        if graph[x][y] != INF: count += 1
    if count == n:
        ans += 1

print(ans)