n, m, h = map(int, input().split())
ladder = [[0] * (n+1) for _ in range(h+1)]
for _ in range(m):
    a, b = map(int, input().split())
    ladder[a][b] = 1

def move():
    for start in range(1, n+1):
        now = start
        for y in range(1, h+1):
            if ladder[y][now] == 1:
                now += 1
            elif ladder[y][now-1] == 1:
                now -= 1
        
        if now != start:
            return False
    
    return True

ans = 4
def dfs(count, y, x):
    global ans
    if ans < count or 3 < count: return
    if move():
        ans = count
        return
    
    for i in range(y, h+1):
        k = x if i == y else 0
        for j in range(k, n):
            if not ladder[i][j] and not ladder[i][j-1] and not ladder[i][j+1]:
                ladder[i][j] = 1
                dfs(count+1, i, j+2)
                ladder[i][j] = 0

dfs(0, 0, 0)
print(ans) if ans < 4 else print(-1)