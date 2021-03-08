# 560ms

import sys

input = sys.stdin.readline
output = sys.stdout.write

n = int(input())
cards = list(map(int, input().rsplit()))

n_dict = {}
for card in cards:
    if card in n_dict.keys():
        n_dict[card] += 1
    else:
        n_dict[card] = 1

m = int(input())
finds = list(map(int, input().rsplit()))

ans = [n_dict[find] if find in n_dict.keys() else 0 for find in finds]
for a in ans:
    output('{} '.format(a))