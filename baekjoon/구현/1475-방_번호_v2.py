# 108ms

n = list(map(int, list(input())))

counter = [0] * 10
for num in n:
    counter[num] += 1

six_nine = counter[6] + counter[9]
counter[6] = six_nine // 2
counter[9] = six_nine - counter[6]

print(max(counter))