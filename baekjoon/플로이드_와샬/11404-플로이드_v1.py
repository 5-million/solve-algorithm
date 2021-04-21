# 228ms
import sys

INPUT = sys.stdin.readline
INF = float('inf')

n = int(INPUT())
m = int(INPUT())
bus = [[INF] * n for _ in range(n)]

for _ in range(m):
    f, t, cost = map(int, INPUT().split())
    bus[f-1][t-1] = min(bus[f-1][t-1], cost)

for f in range(n):
    for t in range(n):
        if f == t: bus[f][t] = 0

for k in range(n):
    for f in range(n):
        for t in range(n):
            bus[f][t] = min(bus[f][t], bus[f][k] + bus[k][t])

for y in range(n):
    for x in range(n):
        if bus[y][x] == INF: print(0, end=" ")
        else: print(bus[y][x], end=" ")
    print()