# 6052ms

import sys
input = sys.stdin.readline
output = sys.stdout.write

t = int(input())
for _ in range(t):
    n = int(input())
    
    rank = [list(map(int, input().rsplit())) for _ in range(n)]
    rank.sort()

    employ = 1
    minRank = rank[0][1]
    for i in range(1, n):
        if rank[i][1] < minRank:
            minRank = rank[i][1]
            employ += 1
    
    output(str(employ) + '\n')