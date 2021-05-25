from collections import deque
from typing import ClassVar

CLOSE = {')': '(', ']': '[', '}': '{'}
def check(s):
    stack = []
    for c in s:
        if c in CLOSE.values():
            stack.append(c)
        else:
            if not stack or stack.pop() != CLOSE[c]:
                return 0
    
    if stack: return 0
    else: return 1

def solution(s):
    s = deque(s)

    ans = 0
    for _ in range(len(s)):
        ans += check(s)
        s.append(s.popleft())
    
    return ans

print('result: 3', solution("[](){}"))
print('result: 2', solution("}]()[{"))
print('result: 0', solution("[)(]"))
print('result: 0', solution("}}}"))