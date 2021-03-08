# 688ms

from collections import Counter
import sys

input = sys.stdin.readline
output = sys.stdout.write

n = int(input())
cards = list(map(int, input().rsplit()))

m = int(input())
finds = list(map(int, input().rsplit()))

counter = Counter(cards)
for find in finds:
    output('{} '.format(counter[find]))