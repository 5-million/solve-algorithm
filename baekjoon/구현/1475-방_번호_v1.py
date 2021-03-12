# 132ms

from collections import Counter

n = list(map(int, list(input())))

counter = Counter(n)
six_nine = counter[6] + counter[9]
counter[6] = six_nine // 2
counter[9] = six_nine - counter[6]

print(max(counter.values()))