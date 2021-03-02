n, m = map(int, input().split())

def bfs(rel, start):
    kebin = [0 for _ in range(n)]
    visit = [0 for _ in range(n)]

    qu = [start]
    visit[start] = 1

    bridge = 1
    while qu:
        _len = len(qu)
        for _ in range(_len):
            cur = qu.pop(0)
            for f in range(n):
                if n == start: continue

                if rel[cur][f] == 1 and visit[f] == 0:
                    kebin[f] = bridge
                    visit[f] = 1
                    qu.append(f)
        
        bridge += 1
        
    return kebin

def kebin(rel):
    kebin = [0 for _ in range(n)]
    for start in range(n):
        kebin[start] = sum(bfs(rel, start))
    
    return kebin.index(min(kebin)) + 1

def main():
    rel = [[0 for _ in range(n)] for _ in range(n)]
    for _ in range(m):
        a, b = map(int, input().split())
        rel[a-1][b-1] = 1
        rel[b-1][a-1] = 1
    
    print(kebin(rel))

main()