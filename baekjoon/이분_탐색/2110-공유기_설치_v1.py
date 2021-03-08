# 172ms

import sys

input = sys.stdin.readline

n, c = map(int, input().rsplit())
homes = [int(input()) for _ in range(n)]
homes.sort()

def canBuild(dist):
    count = 1
    pre_home = homes[0]

    for home in homes[1:]:
        if home - pre_home >= dist:
            pre_home = home
            count += 1
        
        if count == c:
            break
    else:
        return False
    
    return True

def findMinDist(start, end):
    ret = 0
    while start <= end:
        mid = (start + end) // 2

        if canBuild(mid):
            ret = mid
            start = mid + 1
        else:
            end = mid - 1
    
    return ret

print(findMinDist(1, homes[-1] - homes[0]))