from itertools import permutations

n = int(input())
arr = ['{}'.format(i) for i in range(1, n+1)]
for permu in permutations(arr, n):
    print(' '.join(permu))