def solution(n):
    dp = [0, 1, 2]
    for index in range(3, n+1):
        dp.append(dp[index-1] + dp[index-2])
    return dp[n]