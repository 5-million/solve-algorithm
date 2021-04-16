from collections import deque

MAX = 100001

n, k = map(int, input().split())
q = deque()
visit = [False] * MAX
distance = [0] * MAX

q.append(n)
visit[n] = True

while q:
    cur = q.popleft()

    go = cur + 1
    back = cur - 1
    teleport = cur * 2

    if go < MAX and not visit[go]:
        visit[go] = True
        distance[go] = distance[cur] + 1
        q.append(go)
    
    if 0 <= back and not visit[back]:
        visit[back] = True
        distance[back] = distance[cur] + 1
        q.append(back)
    
    if teleport < MAX and not visit[teleport]:
        visit[teleport] = True
        distance[teleport] = distance[cur]
        q.append(teleport)

print(distance[k])