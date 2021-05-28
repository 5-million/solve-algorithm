import sys
import heapq

INPUT =  sys.stdin.readline
n = int(input())
heap = []
for _ in range(n):
    for elem in map(int, INPUT().rsplit()):
        if len(heap) < n:
            heapq.heappush(heap, elem)
        else:
            heapq.heappushpop(heap, elem)

print(heapq.heappop(heap))