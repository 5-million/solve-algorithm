import sys

def solution():
    n = int(input())
    ropes = [int(sys.stdin.readline()) for _ in range(n)]

    ropes.sort(reverse= True)
    for index in range(n):
        ropes[index] = ropes[index] * (index + 1)
    
    return max(ropes)

print(solution())