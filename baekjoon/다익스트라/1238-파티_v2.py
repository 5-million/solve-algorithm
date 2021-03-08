# 180ms

import sys
from collections import deque

INF = float('inf')
input = sys.stdin.readline

n, m, x = map(int, input().rsplit())
_map = [[] for _ in range(n+1)]
r_map = [[] for _ in range(n+1)]

for _ in range(m):
    _from, _to, time = map(int, input().rsplit())
    _map[_from].append([_to, time])
    r_map[_to].append([_from, time])

def dijkstra(start, _map):
    costs = [INF] * (n+1)
    que = deque()
    que.append([start, 0])
    costs[start] = 0

    while que:
        city, time = que.popleft()
        for nxt, t in _map[city]:
            nxt_time = time + t
            if costs[nxt] > nxt_time:
                costs[nxt] = nxt_time
                que.append([nxt, nxt_time])
    
    return costs

home2party = dijkstra(x, r_map)[1:]
party2home = dijkstra(x, _map)[1:]

round_trip = list(map(sum,zip(home2party, party2home)))

print(max(round_trip))