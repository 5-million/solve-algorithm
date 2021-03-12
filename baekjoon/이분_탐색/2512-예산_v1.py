# 112ms

n = int(input())
city = sorted(map(int, input().split()))
budget = int(input())

def isPossbile(maxi):
    if city[-1] < maxi: return False

    total = 0
    for bud in city:
        if bud < maxi: total += bud
        else: total += maxi
    
    if total <= budget: return True
    else: return False


def bs(start, end):
    ret = 0
    while start <= end:
        mid = (start + end) // 2
        if isPossbile(mid):
            ret = max(ret, mid)
            start = mid + 1
        else:
            end = mid - 1

    return ret

print(bs(0, budget))