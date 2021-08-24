import bisect

n = int(input())
sequence = list(map(int, input().split()))
lis = []

for elem in sequence:
    if not lis or lis[-1] < elem:
        lis.append(elem)
    
    lis[bisect.bisect_left(lis, elem)] = elem

print(len(lis))