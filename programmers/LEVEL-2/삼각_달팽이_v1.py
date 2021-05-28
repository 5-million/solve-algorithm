def solution(n):
    direct = [[1, 0], [0, 1], [-1, -1]] # d, r , u

    y, x = -1, 0
    input = 1
    di = 0

    ans = [[0] * i for i in range(1, n+1)]
    for i in range(n, 0, -1):
        dy, dx = direct[di%3]

        for _ in range(i):
            y, x = y+dy, x+dx
            ans[y][x] = input
            input += 1

        di += 1

    return [elem for row in ans for elem in row]    

print(solution(4))
print(solution(5))
print(solution(6))