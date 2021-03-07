import sys
import heapq

INPUT = sys.stdin.readline

INF = float('inf')
n, e = map(int, INPUT().rsplit())
k = int(INPUT())

graph = [[] for _ in range(n+1)]
for _ in range(e):
    u, v, w = map(int, INPUT().rsplit())
    graph[u].append([v,w])

def dijkstra(start):
    q = []
    dist = [INF] * (n+1)
    dist[start] = 0

    heapq.heappush(q, [0, start])
    while q:
        cur_cost, cur_node = heapq.heappop(q)
        for nxt, cost in graph[cur_node]:
            nxt_cost = cur_cost + cost
            
            if dist[nxt] > nxt_cost:
                dist[nxt] = nxt_cost
                heapq.heappush(q, [nxt_cost, nxt])

    return dist[1:]
   
for cost in dijkstra(k):
    print(cost if cost != INF else 'INF')