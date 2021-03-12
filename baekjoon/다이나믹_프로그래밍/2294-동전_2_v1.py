# 140ms

import sys
input = sys.stdin.readline

n, k = map(int, input().rsplit())
coins = [int(input()) for _ in range(n)]
dp = [-1] * (k+1)

for i in range(1, k+1):
    if i in coins:
        dp[i] = 1
    else:
        count = float('inf')
        for coin in coins:
            diff = i - coin
            if diff > 0 and dp[diff] != -1:
                count = min(count, dp[diff] + 1)
        if count != float('inf'):
            dp[i] = count

print(dp[k])