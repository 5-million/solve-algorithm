# 196ms

import sys
import heapq

INF = float('inf')
input = sys.stdin.readline

m, n = map(int, input().rsplit())
miro = [list(input()) for _ in range(n)]
costs = [[INF] * m for _ in range(n)]

def dijkstra():
    dir = [[-1, 0], [0, 1], [1, 0], [0, -1]] # up right down left

    q = [] # [cost, y, x]
    heapq.heappush(q, [0, 0, 0])
    while q:
        cost, cy, cx = heapq.heappop(q)
        if cy == n - 1 and cx == m - 1:
            return cost

        for dy, dx in dir:
            ny = cy + dy
            nx = cx + dx

            if 0 <= ny < n and 0 <= nx < m:
                nxt_cost = cost

                if miro[ny][nx] == '1':
                    nxt_cost += 1
                
                if costs[ny][nx] > nxt_cost:
                    costs[ny][nx] = nxt_cost
                    heapq.heappush(q, [nxt_cost, ny, nx])

print(dijkstra())