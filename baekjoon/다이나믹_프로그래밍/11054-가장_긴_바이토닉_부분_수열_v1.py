n = int(input())
sequense = list(map(int, input().split()))

dp_inc = [1] * n # dp_inc[i] = 0~i번까지 i번 요소를 포함한 증가하는 부분 수열
dp_dec = [1] * n # dp_dec[i] = i~n-1번까지 i번 요소를 포함한 감소하는 부분 수열

for i in range(0, n):
    for j in range(0, i):
        if sequense[i] > sequense[j]:
            dp_inc[i] = max(dp_inc[i], dp_inc[j]+1)

for i in range(n-1, -1, -1):
    for j in range(n-1, i, -1):
        if sequense[i] > sequense[j]:
            dp_dec[i] = max(dp_dec[i], dp_dec[j]+1)

ans = 0
for lis, lds in zip(dp_inc, dp_dec):
    ans = max(ans, lis + lds)

print(ans-1)