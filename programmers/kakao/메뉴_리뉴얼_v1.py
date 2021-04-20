from itertools import combinations
from collections import Counter

def solution(orders, course):
    ans = []
    for crs in course:
        temp = []
        for order in orders:
            combi = combinations(sorted(order), crs)
            temp += combi

        counter = Counter(temp)
        if temp and 1 < max(counter.values()):
            ans += [''.join(comb) for comb in counter if counter[comb] == max(counter.values())]
        
    return sorted(ans)