# 420ms
n = int(input())
forest = [list(map(int, input().split())) for _ in range(n)]

WAYS = [[-1, 0], [0, 1], [1, 0], [0, -1]]
visit = [[False] * n for _ in range(n)]
memoization = [[-1] * n for _ in range(n)]

def dfs(y, x):
    if memoization[y][x] != -1:
        return memoization[y][x]

    ret = 0
    for dy, dx in WAYS:
        ny = y + dy
        nx = x + dx

        if 0 <= ny < n and 0 <= nx < n and not visit[ny][nx] and forest[ny][nx] > forest[y][x]:
            visit[ny][nx] = True
            ret = max(ret, dfs(ny, nx))
            visit[ny][nx] = False
        
    memoization[y][x] = ret + 1
    return memoization[y][x]

def dfs_all():
    ret = 0
    for y in range(n):
        for x in range(n):
            visit[y][x] = True
            ret = max(ret, dfs(y, x))
            visit[y][x] = False
    
    return ret

print(dfs_all())