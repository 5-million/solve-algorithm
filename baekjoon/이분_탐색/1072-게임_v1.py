# 112ms
def win_rate(total, win):
    return int(win * 100 / total)

x, y = map(int, input().split())
z = win_rate(x, y)

if z >= 99:
    print(-1)
    exit()

start, end = 1, x
while start <= end:
    mid = (start + end) // 2

    if win_rate(x + mid, y + mid) > z:
        end = mid - 1
    else:
        start = mid + 1

print(end+1)