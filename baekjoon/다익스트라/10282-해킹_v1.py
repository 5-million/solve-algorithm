import sys
import heapq

input = sys.stdin.readline

INF = float('inf')

def dijkstra(computers, start):
    heap = [[0, start]]
    costs = [INF] * len(computers)
    costs[start] = 0

    while heap:
        cost, now = heapq.heappop(heap)

        if costs[now] < cost:
            continue
        
        for nxt_cost, nxt in computers[now]:
            if cost + nxt_cost < costs[nxt]:
                heapq.heappush(heap, [cost+ nxt_cost, nxt])
                costs[nxt] = cost + nxt_cost
    
    infection_count = 0
    time = 0
    for cost in costs:
        if cost != INF:
            infection_count += 1
            time = max(time, cost)

    return infection_count, time

t = int(input())
for _ in range(t):
    n, d, c = map(int, input().split())
    computers = [[] for _ in range(n+1)]

    for _ in range(d):
        a, b, s = map(int, input().split())
        computers[b].append([s, a])
    
    infection_count, time = dijkstra(computers, c)
    print(infection_count, time)