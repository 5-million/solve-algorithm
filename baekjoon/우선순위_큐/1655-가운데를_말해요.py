import sys
import heapq

input = sys.stdin.readline

n = int(input())
left_heap, right_heap = [], []

for _ in range(n):
    number = int(input())
    if len(left_heap) == len(right_heap):
        heapq.heappush(left_heap, (-number, number))
    else:
        heapq.heappush(right_heap, number)
    
    if left_heap and right_heap and left_heap[0][1] > right_heap[0]:
        max_value = heapq.heappop(left_heap)[1]
        min_value = heapq.heappop(right_heap)

        heapq.heappush(left_heap, (-min_value, min_value))
        heapq.heappush(right_heap, max_value)
    
    print(left_heap[0][1])