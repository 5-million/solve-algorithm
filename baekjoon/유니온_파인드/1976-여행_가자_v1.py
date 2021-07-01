def union(a, b):
    a = find(a)
    b = find(b)

    if a < b:
        parent[b] = a
    else:
        parent[a] = b

def find(child):
    if parent[child] != child:
        parent[child] = find(parent[child])
    return parent[child]

n = int(input())
m = int(input())

parent = [i for i in range(n+1)]

for i in range(n):
    row = list(map(int, input().split()))
    for j in range(n):
        if row[j] == 1:
            union(i+1, j+1)

target_cities = list(set(map(int, input().split())))

root = find(target_cities[0])
for city in target_cities[1:]:
    if root != find(city):
        print('NO')
        break
else:
    print('YES')