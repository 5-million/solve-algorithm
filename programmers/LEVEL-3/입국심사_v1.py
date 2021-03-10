def isPossible(n, times, time):
    count = 0
    for t in times:
        count += time // t
        if count >= n: return True

    return False

def binarySearch(n, times, start, end):
    ans = 0
    while start <= end:
        time = (start + end) // 2
        if isPossible(n, times, time):
            ans = time
            end = time - 1
        else: start = time + 1
    
    return ans

def solution(n, times):
    ans = binarySearch(n, times, 1, min(times) * n)
    return ans