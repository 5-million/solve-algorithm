n = int(input())
sequence = list(map(int,input().split()))

dp = [0] * n
dp[0] = sequence[0]

for i in range(n):
    dp[i] = sequence[i]
    for j in range(i):
        if sequence[i] > sequence[j]:
            dp[i] = max(dp[i], dp[j]+sequence[i])

print(max(dp))