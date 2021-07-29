t = int(input())

for _ in range(t):
    case, m = map(int, input().split())
    
    a = 1
    b = 1
    i = 2
    while True:
        i += 1
        a, b = b, (a+b) % m
        if a == 1 and b == 1:
            print(case, i-2)
            break


# 5
# 1 4
# 2 5
# 3 11
# 4 123456
# 5 987654