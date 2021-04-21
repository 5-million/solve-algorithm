# 552ms
def calculate_distance(p1, p2):
    return abs(p2[0] - p1[0]) + abs(p2[1] - p1[1])

def can_go(p1, p2):
    if calculate_distance(p1, p2) <= 1000: return True
    else: return False

t = int(input())
for _ in range(t):
    cs_count = int(input()) # 편의점 갯수
    nodes = [list(map(int, input().split()))]
    nodes += [list(map(int, input().split())) for _ in range(cs_count)]
    nodes += [list(map(int, input().split()))]

    node_count = len(nodes)
    graph = [[False] * node_count for _ in range(node_count)]

    ans = False
    for y in range(node_count):
        for x in range(node_count):
            if y == x: graph[y][x] = False
            else: graph[y][x] = can_go(nodes[y], nodes[x])
    
    for k in range(node_count):
        for y in range(node_count):
            for x in range(node_count):
                if y != x and not graph[y][x]: graph[y][x] = graph[y][k] & graph[k][x]
                if graph[0][node_count-1]:
                    ans = True

    print('happy') if ans else print('sad')