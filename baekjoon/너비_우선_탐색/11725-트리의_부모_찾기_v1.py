# 324ms

import sys
from collections import deque

input = sys.stdin.readline

n = int(input())
graph = [[] for _ in range(n+1)]
visit = [False] * (n+1)
for _ in range(n-1):
    node1, node2 = map(int, input().rsplit())
    graph[node1].append(node2)
    graph[node2].append(node1)

q = deque()
q.append(1)
visit[1] = True

parent = [0] * (n+1)
while q:
    cur = q.popleft()
    for nxt in graph[cur]:
        if not visit[nxt]:
            parent[nxt] = cur
            visit[nxt] = True
            q.append(nxt)

for p in parent[2:]:
    print(p)