all_routes = []

def dfs(tickets, routes, use):
    if len(routes) == len(tickets) + 1:
        all_routes.append(routes)
        return
    
    cur = routes[-1]
    for idx, [frm, to] in enumerate(tickets):
        if frm == cur and not use[idx]:
            use[idx] = True
            dfs(tickets, routes + [to], use)
            use[idx] = False


def solution(tickets):
    dfs(tickets, ["ICN"], [False] * len(tickets))
    return sorted(all_routes)[0]