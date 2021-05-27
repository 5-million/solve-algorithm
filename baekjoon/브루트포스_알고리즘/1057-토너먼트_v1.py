n, jimin, hansu = map(int, input().split())

ans = 0
while jimin != hansu:
    jimin = (jimin + 1) // 2
    hansu = (hansu + 1) // 2
    
    ans += 1

print(ans)