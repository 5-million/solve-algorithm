import heapq

INF = float('inf')

def solution(N, road, K):
    town = [[INF] * (N+1) for _ in range(N+1)]
    for a, b, c in road:
        town[a][b] = min(town[a][b], c)
        town[b][a] = min(town[b][a], c)
    
    distance = [INF] * (N+1)
    heap = []

    distance[1] = 0
    heapq.heappush(heap, [0, 1])
    
    while heap:
        cost, now = heapq.heappop(heap)

        for nxt in range(1, N+1):
            nxt_cost = cost + town[now][nxt]
            if town[now][nxt] != INF and nxt_cost < distance[nxt]:
                distance[nxt] = nxt_cost
                heapq.heappush(heap, [nxt_cost, nxt])
    
    ans = 0
    print(distance)
    for dist in distance:
        if dist <= K:
            ans += 1

    return ans

print('result: 4', solution(5, [[1,2,1],[2,3,3],[5,2,2],[1,4,2],[5,3,1],[5,4,2]], 3))
print('result: 4', solution(6, [[1,2,1],[1,3,2],[2,3,2],[3,4,3],[3,5,2],[3,5,3],[5,6,1]], 4))