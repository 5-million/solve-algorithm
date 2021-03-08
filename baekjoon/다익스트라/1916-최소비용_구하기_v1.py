import sys
from collections import deque

INF = float('inf')

n = int(input()) # 도시의 갯수
m = int(input()) # 버스의 갯수

buses = [[] for _ in range(n+1)]
for _ in range(m):
    _from, _to, cost = map(int, sys.stdin.readline().rsplit())
    buses[_from].append([_to, cost])

start, end = map(int, sys.stdin.readline().rsplit())

def dijkstra(start):
    costs = [INF] * (n+1)
    costs[start] = 0

    q = deque()
    q.append([start, 0])

    while q:
        station, cost = q.popleft()
        for nxt, c in buses[station]:
            nxt_cost = cost + c

            if costs[nxt] > nxt_cost:
                costs[nxt] = nxt_cost
                q.append([nxt, nxt_cost])
    
    return costs

print(dijkstra(start)[end])