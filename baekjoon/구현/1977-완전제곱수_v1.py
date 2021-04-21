# 112ms
start = int(input())
end = int(input())

hap = 0
mini = end
for num in range(start, end+1):
    square = num ** 0.5
    if square == int(square):
        hap += num
        mini = min(mini, num)

if hap != 0:
    print(hap)
    print(mini)
else:
    print(-1)