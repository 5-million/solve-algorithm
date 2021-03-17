# 192ms

import sys
input = sys.stdin.readline

n = int(input())
stack = []
for _ in range(n):
    oper = input().split()
    if oper[0] == 'push':
        stack.append(int(oper[1]))
    elif oper[0] == 'pop':
        print(stack.pop()) if stack else print(-1)
    elif oper[0] == 'size':
        print(len(stack))
    elif oper[0] == 'empty':
        print(0) if stack else print(1)
    else:
        print(stack[-1]) if stack else print(-1)