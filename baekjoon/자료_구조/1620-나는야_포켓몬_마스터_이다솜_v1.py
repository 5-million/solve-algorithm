# 360ms

import sys

INPUT = sys.stdin.readline
OUTPUT = sys.stdout.write

n, m = map(int, input().split())
dick = {}

idx = 1
for _ in range(n):
    pokemon = INPUT().rstrip()
    dick[str(idx)] = pokemon
    dick[pokemon] = idx
    idx += 1

for _ in range(m):
    x = INPUT().rstrip()
    OUTPUT("{0}\n".format(dick[x]))