import heapq

def solution(operations):
    heap = []
    for oper in operations:
        print(heap)
        if oper == 'D 1':
            if heap:
                heap.pop(heap.index(heapq.nlargest(1, heap)[0]))
        elif oper == 'D -1':
            if heap: heapq.heappop(heap)
        else:
            num = int(oper.split()[1])
            heapq.heappush(heap, num)

    return [heapq.nlargest(1, heap)[0], heapq.nsmallest(1, heap)[0]] if heap else [0, 0]