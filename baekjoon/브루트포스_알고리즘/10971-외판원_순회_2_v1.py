n = int(input())
costs = [list(map(int, input().split())) for _ in range(n)]

def get_minimum_cost(start, now, visit, cost):
    if visit.count(True) == n-1:
        if costs[now][start] == 0:
            return float('inf')
        else:
            return cost + costs[now][start]

    ret = float('inf')
    for nxt in range(n):
        if start != nxt and costs[now][nxt] != 0 and not visit[nxt]:
            visit[nxt] = True
            ret = min(ret, get_minimum_cost(start, nxt, visit, cost + costs[now][nxt]))
            visit[nxt] = False
    
    return ret

ans = float('inf')
for start in range(n):
    ans = min(ans, get_minimum_cost(start, start, [False] * n, 0))

print(ans)