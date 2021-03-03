def solution():
    INF = float('inf')
    n = int(input())
    dp = [INF] * (n+1)
    dp[0] = 0
    dp[1] = 1

    for index in range(2, n+1):
        square_root = int(index ** 0.5)

        for j in range(1, square_root+1):
            square = j ** 2
            dp[index] = min(dp[index], dp[index - square] + 1)
    
    return dp[n]

print(solution())