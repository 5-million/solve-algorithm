m, n = map(int, input().split()) # m : 세로, n : 가로
map_ = [list(map(int, input().split())) for _ in range(m)]
visit = [[-1] * n for _ in range(m)]

WAYS = [[-1, 0,], [0, 1], [1, 0], [0, -1]]

def dfs(y, x):
    if visit[y][x] != -1:
        return visit[y][x]
    
    if [y, x] == [m-1, n-1]:
        return 1

    visit[y][x] = 0
    for wy, wx in WAYS:
        ny, nx = y + wy, x + wx

        if 0 <= ny < m and 0 <= nx < n and map_[ny][nx] < map_[y][x]:
            visit[y][x] += dfs(ny, nx)
    
    return visit[y][x]

print(dfs(0, 0))