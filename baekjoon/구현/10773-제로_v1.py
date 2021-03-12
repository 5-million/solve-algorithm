# 128ms

import sys
input = sys.stdin.readline

k = int(input())
stack = []
for i in range(k):
    ip = int(input())
    
    if ip: stack.append(ip)
    else: stack.pop()

print(sum(stack))