def recursive(a, b, c):
    if dp[a][b][c] != 0:
        return dp[a][b][c]
    else:
        ret = 0
        if a <= 50 or b <= 50 or c <= 50: ret = 1
        elif a > 70 or b > 70 or c > 70: ret = recursive(70, 70, 70)
        elif a < b and b < c:
            ret = recursive(a, b, c-1) + recursive(a, b-1, c-1) - recursive(a, b-1, c)
        else:
            ret = recursive(a-1, b, c) + recursive(a-1, b-1, c) + recursive(a-1, b, c-1) - recursive(a-1, b-1, c-1)
        
        dp[a][b][c] = ret

        return ret

a, b, c = map(int, input().split())
dp = [[[0 for _ in range(101)] for _ in range(101)] for _ in range(101)]

while (a != -1 or b != -1 or c != -1):
    a += 50
    b += 50
    c += 50

    print('w({0}, {1}, {2}) = {3}'.format(a-50, b-50, c-50, recursive(a, b, c)))

    a, b, c = map(int, input().split())