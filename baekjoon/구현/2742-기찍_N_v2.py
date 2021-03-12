# 124ms

import sys
output = sys.stdout.write

n = int(input())
for i in range(n, 0, -1):
    output(str(i) + '\n')