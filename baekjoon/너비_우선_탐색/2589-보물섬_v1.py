from collections import deque

n, m = map(int, input().split())
treasure_map = [list(input()) for _ in range(n)]
direction = [[-1, 0], [0, 1], [1, 0], [0, -1]] # up right down left

def get_furthest_distance(start):
    qu = deque()
    qu.append(start)
    visit = [[False] * m for _ in range(n)]

    visit[start[0]][start[1]] = True

    time = -1
    while qu:
        qu_len = len(qu)
        time += 1
        for _ in range(qu_len):
            y, x = qu.popleft()

            for dy, dx in direction:
                ny = y + dy
                nx = x + dx

                if 0 <= ny < n and 0 <= nx < m and treasure_map[ny][nx] == 'L' and not visit[ny][nx]:
                    visit[ny][nx] = True
                    qu.append([ny, nx])

    return time

ans = 0
for y in range(n):
    for x in range(m):
        if treasure_map[y][x] == 'L':
            ans = max(ans, get_furthest_distance([y, x]))

print(ans)