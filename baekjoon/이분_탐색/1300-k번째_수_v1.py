n = int(input())
k = int(input())

start, end = 1, n*n
answer = 0
while start <= end:
    mid = (start + end) // 2

    acc = 0
    for i in range(1, n+1):
        acc += min(mid//i, n) # mid보다 작거나 같은 수의 갯수
        
    if acc >= k:
        answer = mid # B[k] = mid
        end = mid - 1
    else:
        start = mid + 1

print(answer)

# 예를 들어 n = 3, k = 7인 경우
# 5보다 작거나 같은 수의 갯수가 6개이므로 B[k]는 5보다 크다.
# 7보다 작거나 같은 수의 갯수가 8개다. 그럼 k는 8개 안에 포함되므로 B[k] = 7이다.
# 6보다 작거나 같은 수의 갯수가 8개다. 6에서 7로 올랐을 때 갯수의 변화가 없으므로 7은 존재하지 않는다.
# 즉, 최대값이 6이고 길이가 8인 리스트에서 k는 길이인 8보다 작고 B[k]는 5보다 크기때문에 B[k] = 6이다.