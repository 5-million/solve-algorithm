n, k = map(int, input().split())
items = [list(map(int, input().split())) for _ in range(n)]
dp = [[0] * (k+1) for _ in range(n)]

for idx, [w, v] in enumerate(items):
    for weight in range(1, k+1):
        if weight < w:
            dp[idx][weight] = dp[idx-1][weight]
        
        else:
            dp[idx][weight] = max(dp[idx-1][weight], dp[idx-1][weight-w] + v)

print(dp[n-1][k])