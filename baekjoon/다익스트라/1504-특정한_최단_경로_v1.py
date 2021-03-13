# 492ms

import sys
from collections import deque

input = sys.stdin.readline
INF = float('inf')

n, e = map(int, input().rsplit())
edges = [[] for _ in range(n+1)]
all_costs = [[] for _ in range(n+1)]

for _ in range(e):
    n1, n2, cost = map(int, input().rsplit())
    edges[n1].append([n2, cost])
    edges[n2].append([n1, cost])

def dijkstra(start):
    q = deque()
    q.append([start, 0])
    costs = [INF] * (n+1)
    costs[start] = 0

    while q:
        cur, cost = q.popleft()
        for nxt, nc in edges[cur]:
            nxt_cost = cost + nc

            if costs[nxt] > nxt_cost:
                costs[nxt] = nxt_cost
                q.append([nxt, nxt_cost])
    
    return costs

v1, v2 = map(int, input().rsplit())

all_costs[1] = dijkstra(1)
all_costs[v1] = dijkstra(v1)
all_costs[v2] = dijkstra(v2)

route1 = all_costs[1][v1] + all_costs[v1][v2] + all_costs[v2][n]
route2 = all_costs[1][v2] + all_costs[v2][v1] + all_costs[v1][n]

min_cost = min(route1, route2)
print(min_cost) if min_cost != INF else print(-1)