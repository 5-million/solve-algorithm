## n번 선수가 이긴 선수의 수와 n번 선수에게 진 선수의 수가
## 자신을 제외한 전체 선수의 수와 동일할 때 순위를 정할 수 있음

from collections import deque

def bfs(n, graph, start):
    q = deque()
    q.append(start)

    visit = [False] * (n+1)
    visit[start]= True

    can_visit = 0
    while q:
        cur = q.popleft()
        for nxt in graph[cur]:
            if not visit[nxt]:
                visit[nxt] = True
                can_visit += 1
                q.append(nxt)
    
    return can_visit

def solution(n, results):
    wins = [[] for _ in range(n+1)] # wins[idx] = idx가 이긴 선수 리스트
    loses = [[] for _ in range(n+1)] # wins[dix] = idx에게 진 선수 리스트

    for win, lose in results:
        wins[win].append(lose)
        loses[lose].append(win)
    
    ans = 0
    for node in range(1, n+1):
        if bfs(n, wins, node) + bfs(n, loses, node) == n - 1:
            ans += 1

    return ans