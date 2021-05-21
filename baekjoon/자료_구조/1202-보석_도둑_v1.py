import heapq

n, k = map(int, input().split())
gems = []
for _ in range(n):
    heapq.heappush(gems, list(map(int, input().split())))

bags = sorted([int(input()) for _ in range(k)])

ans = 0
temp_gems = []
for bag in bags:
    while gems and bag >= gems[0][0]:
        heapq.heappush(temp_gems, -heapq.heappop(gems)[1])
    
    if temp_gems:
        ans -= heapq.heappop(temp_gems)

print(ans)