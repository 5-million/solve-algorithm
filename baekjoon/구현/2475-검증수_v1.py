# 112ms

import sys
input = sys.stdin.readline

verification_num = list(map(int, input().rsplit()))
print(sum([num ** 2 for num in verification_num]) % 10)
