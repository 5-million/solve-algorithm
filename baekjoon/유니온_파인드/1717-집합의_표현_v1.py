import sys
sys.setrecursionlimit(10**5)

def union(a, b):
    a = find_parent(a)
    b = find_parent(b)

    if a < b:
        parent[b] = a
    else:
        parent[a] = b

def find_parent(child):
    if parent[child] != child:
        parent[child] = find_parent(parent[child])
    
    return parent[child]

def is_same_set(a, b):
    a = find_parent(a)
    b = find_parent(b)

    if a == b:
        print('YES')
    else:
        print('NO')

n, m = map(int, input().split())
parent = [i for i in range(n+1)]
for _ in range(m):
    oper, a, b = map(int, input().split())

    if oper == 0:
        union(a, b)
    else:
        is_same_set(a, b)