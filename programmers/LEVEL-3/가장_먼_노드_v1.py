import heapq
INF = float('inf')

def dijkstra(graph):
    costs = [INF] * len(graph)
    q = [[0, 0]]
    costs[0] = 0
    while q:
        cost, cur = heapq.heappop(q)
        for nxt in graph[cur]:
            nxt_cost = cost + 1

            if costs[nxt] > nxt_cost:
                costs[nxt] = nxt_cost
                heapq.heappush(q, [nxt_cost, nxt])
    
    return costs

def solution(n, edge):
    graph = [[] for _ in range(n)]
    for node1, node2 in edge:
        graph[node1-1].append(node2-1)
        graph[node2-1].append(node1-1)
    
    dist = dijkstra(graph)
    return dist.count(max(dist))