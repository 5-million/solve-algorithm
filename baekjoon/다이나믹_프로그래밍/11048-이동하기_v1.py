# 308ms

import sys
input = sys.stdin.readline

n, m = map(int, input().rsplit())
dp = [[0] * (m+1)]
for _ in range(n):
    apnd = [0] + list(map(int, input().rsplit()))
    dp.append(apnd)

for y in range(1, n+1):
    for x in range(1, m+1):
        here = dp[y][x]
        dp[y][x] = max(dp[y-1][x], dp[y][x-1])
        dp[y][x] = max(dp[y][x], dp[y-1][x-1])
        dp[y][x] += here

print(dp[n][m])