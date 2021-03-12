# 964ms

import sys
input = sys.stdin.readline

t = int(input())
for _ in range(t):
    n = int(input())
    rank = [0] * (n+1)
    for _ in range(n):
        i, r = map(int, input().rsplit())
        rank[i] = r

    minRank = rank[1]
    for r in rank[2:]:
        if r < minRank:
            minRank = r
        else: n -= 1

    print(n)