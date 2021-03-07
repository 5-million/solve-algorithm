# Kruskal Algorithm
def solution(n, costs):
    costs.sort(key = lambda x: x[2])
    visit = set([costs[0][0]])

    ans = 0
    while len(visit) != n:
        for _from, _to, cost in costs:
            if _from in visit and _to in visit:
                continue
            if _from in visit or _to in visit:
                visit.update([_from, _to])
                ans += cost
                break

    return ans