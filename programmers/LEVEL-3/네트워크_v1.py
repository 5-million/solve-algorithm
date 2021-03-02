def solution(n, computers):
    visit = [0] * n
    ans = 0

    def dfs(cur):
        for idx in range(n):
            if computers[cur][idx] == 1 and visit[idx] == 0:
                visit[idx] = 1
                dfs(idx)

    for idx in range(n):
        if visit[idx] == 0:
            visit[idx] = 1
            dfs(idx)
            ans += 1

    return ans