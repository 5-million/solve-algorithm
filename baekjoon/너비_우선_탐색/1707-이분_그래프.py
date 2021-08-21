from collections import deque

EMPTY = 0
RED = 1
BLUE = 2

def bfs(graph, color, start):
    q = deque()
    q.append([start, RED])
    color[start] = RED

    while q:
        now = q.popleft()
        for nxt in graph[now[0]]:
            if color[nxt] == now[1]:
                return False
            
            if color[nxt] == EMPTY:
                color[nxt] = BLUE if now[1] == RED else RED
                q.append([nxt, color[nxt]])

    return True

def solution():
    v, e = map(int, input().split())
    graph = [[] for _ in range(v+1)]
    color = [EMPTY for _ in range(v+1)]

    for _ in range(e):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)

    for i in range(1, v+1):
        if color[i] == EMPTY:
            if not bfs(graph, color, i):
                return False

    return True

k = int(input())
for _ in range(k):
    if solution():
        print("YES")
    else:
        print("NO")