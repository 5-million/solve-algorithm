import copy
from collections import deque
from itertools import combinations

n, m = map(int, input().split())
virus = []
lab = []
empty_count = 0

for i in range(n):
    row = list(map(int, input().split()))
    for j, elem in enumerate(row):
        if elem == 2:
            virus.append((i, j))
        elif elem == 0:
            empty_count += 1

    lab.append(row)

direction = [(-1, 0), (0, 1), (1, 0), (0, -1)]

def solution():
    if empty_count == 0: return 0
    
    virus_combination = combinations(virus, m)

    ans = float('inf')
    for vc in virus_combination:
        result = bfs(vc)
        if result != -1:
            ans = min(ans, result)
    
    if ans == float('inf'): return -1
    else: return ans

def bfs(activate_virus):
    temp = copy.deepcopy(lab)
    infection_count = 0
    qu = deque()

    for i in range(m):
        av_y, av_x = activate_virus[i]
        temp[av_y][av_x] = 1
        qu.append((av_y, av_x))
    
    time = 0
    while qu:
        qu_len = len(qu)
        time += 1

        for _ in range(qu_len):
            y, x = qu.popleft()

            for dy, dx in direction:
                ny = y + dy
                nx = x + dx

                if 0 <= ny < n and 0 <= nx < n and temp[ny][nx] != 1:
                    if temp[ny][nx] == 0:
                        infection_count += 1

                    temp[ny][nx] = 1
                    qu.append((ny, nx))
    
                    if infection_count == empty_count:
                        return time
    return -1

print(solution())