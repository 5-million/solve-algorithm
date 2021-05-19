from collections import deque

def solution(maps):
    n = len(maps)
    m = len(maps[0])

    DIREC = [[-1, 0], [0, 1], [1, 0], [0, -1]] # up right down left
    qu = deque()
    visit = [[False] * m for _ in range(n)]

    qu.append([0, 0])
    visit[0][0] = True

    count = 0
    while qu:
        qu_len = len(qu)
        count += 1
        for _ in range(qu_len):
            y, x = qu.popleft();

            if [y, x] == [n-1, m-1]:
                return count

            for dy, dx in DIREC:
                ny, nx = y + dy, x + dx

                if 0 <= ny < n and 0 <= nx < m and maps[ny][nx] == 1 and not visit[ny][nx]:
                    visit[ny][nx] = True
                    qu.append([ny, nx])
    
    return -1

print('answer : 11', solution([[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,1],[0,0,0,0,1]]))