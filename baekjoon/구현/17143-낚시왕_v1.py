r, c, m = map(int, input().split())

direction = {1: [-1, 0], 2: [1, 0], 3: [0, 1], 4: [0, -1]}
reverse_direction = {1: 2, 2: 1, 3: 4, 4: 3}

sharks = [-1]
for _ in range(m):
    y, x, s, d, z = map(int, input().split())
    sharks.append([y, x, s, d, z])

def move_shark():
    temp = [[-1] * (c+1) for _ in range(r+1)]
    for id, shark in enumerate(sharks):
        if shark == -1:
            continue

        y, x, s, d, z = shark
        if d == 1:
            s -= y-1 # 시작점을 1로, 방향을 아래쪽으로 만들기 위해
            y = 1
            d = 2
        
        if d == 2:
            s += y-1 # 시작점을 1로 만들기 위해
            y = 1
            mok, rem = divmod(s, r-1)

            if mok % 2 == 0:
                y += rem
            else:
                y = r - rem
                d = 1
        
        if d == 4:
            s -= x-1 # 시작점을 1로, 방향을 오른쪽으로 만들기 위해
            x = 1
            d = 3

        if d == 3:
            s += x-1 # 시작점을 1로 만들기 위해
            x = 1
            mok, rem = divmod(s, c-1)

            if mok % 2 == 0:
                x += rem
            else:
                x = c - rem
                d = 4

        sharks[id][0] = y
        sharks[id][1] = x
        sharks[id][3] = d

        if temp[y][x] != -1:
            if sharks[temp[y][x]][4] < z:
                sharks[temp[y][x]] = -1
                temp[y][x] = id
            else:
                sharks[id] = -1
        else:
            temp[y][x] = id

angler = 0
catch = 0
while angler < c:
    angler += 1

    temp = [0, r+1, 0]
    for id, shark in enumerate(sharks):
        if shark == -1:
            continue
        if shark[1] == angler and shark[0] < temp[1]:
            temp = [id, shark[0], shark[4]]

    if temp[0] != 0:
        catch += temp[2]
        sharks[temp[0]] = -1
    
    move_shark()

print(catch)