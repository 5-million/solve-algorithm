import heapq

def solution(n, works):
    heap = []
    for index, work in enumerate(works):
        heapq.heappush(heap, (-work, work, index))
    
    for _ in range(n):
        _, work, index = heapq.heappop(heap)
        work -= 1
        works[index] = work
        heapq.heappush(heap, (-work, work, index))
    
    return sum([work ** 2 for work in works if work > 0])