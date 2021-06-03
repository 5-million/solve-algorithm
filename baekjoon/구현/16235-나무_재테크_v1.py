import sys

input = sys.stdin.readline

n, m, k = map(int, input().rsplit())
nutrients = [list(map(int, input().rsplit())) for _ in range(n)]
land = [[5] * n for _ in range(n)]
tree = [[[] for _ in range(n)] for _ in range(n)] 

for _ in range(m):
    x, y, z = map(int, input().rsplit())
    tree[x-1][y-1].append(z)

around = [[-1, -1], [-1, 0], [-1, 1], [0, -1], [0, 1], [1, -1], [1, 0], [1, 1]]

for _ in range(k):
    # spring and summer
    for y in range(n):
        for x in range(n):
            if not tree[y][x]:
                continue

            temp = []
            dead = 0
            tree[y][x].sort()
            for tree_age in tree[y][x]:
                if tree_age <= land[y][x]:
                    land[y][x] -= tree_age
                    temp.append(tree_age + 1)
                else:
                    dead += tree_age // 2
            
            tree[y][x] = temp
            land[y][x] += dead

    # fall
    for y in range(n):
        for x in range(n):
            for tree_age in tree[y][x]:
                if tree_age % 5 == 0:
                    for dy, dx in around:
                        ny = y + dy
                        nx = x + dx

                        if 0 <= ny < n and 0 <= nx < n:
                            tree[ny][nx].append(1)
        
    # winter
    for y in range(n):
        for x in range(n):
            land[y][x] += nutrients[y][x]

ans = 0
for y in range(n):
    for x in range(n):
        ans += len(tree[y][x])

print(ans)