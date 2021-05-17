INF = float('inf')

v, e = map(int, input().split())
distances = [[INF] * v for _ in range(v)]

for _ in range(e):
    a, b, c = map(int, input().split())
    distances[a-1][b-1] = c

for idx in range(v):
    distances[idx][idx] = 0

cycles = [INF] * v
for k in range(v):
    for y in range(v):
        for x in range(v):
            if y == x: continue
            distances[y][x] = min(distances[y][x], distances[y][k] + distances[k][x])
            cycles[y] = min(cycles[y], distances[y][x] + distances[x][y])

ans = INF
for cycle in cycles:
    ans = min(ans, cycle)

print(ans) if ans != INF else print(-1) 