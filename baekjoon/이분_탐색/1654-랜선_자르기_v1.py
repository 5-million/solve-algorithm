import sys
INPUT = sys.stdin.readline

k, n = map(int, input().split())
cables = [int(INPUT()) for _ in range(k)]
cables.sort()

def getCableCount(cut_len):
    count = 0
    for cable in cables:
        count += cable // cut_len
    
    return count

def findMaxCableLength(start, end):
    max_len = 0
    while start <= end:
        mid = (start + end) // 2
        cable_count = getCableCount(mid)

        if cable_count >= n:
            max_len = max(max_len, mid)
            start = mid + 1
        else: end = mid - 1

    return max_len

print(findMaxCableLength(1, cables[-1]))
