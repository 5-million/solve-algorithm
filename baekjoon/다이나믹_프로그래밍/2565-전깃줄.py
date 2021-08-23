wire_count = int(input())
connect = [0 for _ in range(501)]

for _ in range(wire_count):
    a, b = map(int, input().split())
    connect[a] = b

sequence = [conn for conn in connect if conn != 0]

def lis():
    dp = [1 for _ in range(wire_count)]

    for i in range(wire_count):
        for j in range(0, i):
            if sequence[j] < sequence[i]:
                dp[i] = max(dp[i], dp[j] + 1)
    
    return max(dp)

print(wire_count - lis())