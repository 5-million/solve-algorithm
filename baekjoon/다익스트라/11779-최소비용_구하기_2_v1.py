import heapq

INF = float('inf')

n = int(input())
m = int(input())
buses = [[] for _ in range(n+1)]

for _ in range(m):
    f, t, c = map(int, input().split())
    buses[f].append([t, c])

start, destination = map(int, input().split())

heap = []
costs = [INF] * (n + 1)

heapq.heappush(heap, [0, start, [start]])
costs[start] = 0

result_path = []
while heap:
    cost, now, path = heapq.heappop(heap);

    if now == destination:
        result_path = path
        break

    if costs[now] < cost:
        continue

    for t, c in buses[now]:
        if costs[t] > costs[now] + c:
            costs[t] = costs[now] + c
            heapq.heappush(heap, [costs[t], t, path + [t]])

print(costs[destination])
print(len(result_path))
print(' '.join(map(str, result_path)))