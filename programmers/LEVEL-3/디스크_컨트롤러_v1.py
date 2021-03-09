import heapq

def solution(jobs):
    heap = []
    start, end = -1, 0
    total, done = 0, 0
    while done < len(jobs):
        for req_time, working_time in jobs:
            if start < req_time <= end:
                heapq.heappush(heap, [working_time, req_time])
        
        start = end
        if heap:
            working_time, req_time = heapq.heappop(heap)
            end += working_time
            done += 1
            total += (end - req_time)
        else:
            end += 1

    return total // len(jobs)