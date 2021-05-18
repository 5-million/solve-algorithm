from collections import deque    

n, l = map(int, input().split())
m = []
for _ in range(n):
    m.append(list(map(int, input().split())))

def can_build_up(hight, now):
    # 올라가는 경사로를 놓는 경우
    if now+l < n and hight[now]+1 == hight[now+l]:
        for index in range(now+1, now+l):
            if hight[now] != hight[index]:
                return False, now
    
        return True, now+l
    return False, now

def can_build_down(hight, now):
    # 내려가는 경사로를 놓는 경우
    if now+l < n and hight[now+1] == hight[now]-1:
        for index in range(now+1, now+l+1):
            if hight[now+1] != hight[index]:
                return False, now

        if now+l == n-1 or (now+l+1 < n and hight[now+l]-1 == hight[now+l+1]):
            return True, now+l

        if now+l+1 < n and hight[now+l] == hight[now+l+1]:
            return True, now+l+1

    return False, now

def is_possible(hight):
    qu = deque()
    visit = [False] * n

    qu.append(0)
    visit[0] = True

    while qu:
        now = qu.popleft()

        if now == n-1:
            return 1

        if now+1 < n and hight[now] == hight[now+1] and not visit[now+1]:
            visit[now+1] = True
            qu.append(now+1)
        
        up_runway, up_dest = can_build_up(hight, now)
        if up_runway and not visit[up_dest]:
            visit[up_dest] = True
            qu.append(up_dest)
        
        down_runway, down_dest = can_build_down(hight, now)
        if down_runway and not visit[down_dest]:
            visit[down_dest] = True
            qu.append(down_dest)
    
    return 0

ans = 0
for i in range(n):
    col = m[i]
    row = [m[y][i] for y in range(n)]

    ans += is_possible(col)
    ans += is_possible(row)

print(ans)

# for a in m:
#     print(a)
# print('---------------------')
# row = [m[y][5] for y in range(n)]
# print(row)

# 6 2
# 3 2 1 1 2 3
# 3 2 2 1 2 3
# 3 2 2 2 3 3
# 3 3 3 3 3 3
# 3 3 3 3 2 2
# 3 3 3 3 2 2