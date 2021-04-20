from collections import deque

r, c = map(int, input().split()) # r: 세로, c: 가로
map_ = []

water, hog = [], []
for idx in range(r):
    row = list(input())
    map_.append(row)
    if '*' in row: water.append([idx, row.index('*')])
    if 'S' in row: hog = [idx, row.index('S')]

ways = [[-1, 0], [0, 1], [1, 0], [0, -1]]

def extend_water():
    temp = water[:]
    for y, x in temp:
        for dy, dx in ways:
            ny, nx = y + dy, x + dx

            if 0 <= ny < r and 0 <= nx < c and map_[ny][nx] == '.':
                map_[ny][nx] = '*'
                water.append([ny, nx])

def solution():
    q = deque()
    visit = [[False] * c for _ in range(r)]

    q.append(hog)
    visit[hog[0]][hog[1]] = True

    time = 0
    while q:
        q_len = len(q)
        extend_water()
        for _ in range(q_len):
            cy, cx = q.popleft()

            for dy, dx in ways:
                ny, nx = cy + dy, cx + dx

                if 0 <= ny < r and 0 <= nx < c and not visit[ny][nx]:
                    if map_[ny][nx] == '.':
                        visit[ny][nx] = True
                        q.append([ny, nx])

                    elif map_[ny][nx] == 'D':
                        return time + 1

        time += 1
    
    return 'KAKTUS'

print(solution())