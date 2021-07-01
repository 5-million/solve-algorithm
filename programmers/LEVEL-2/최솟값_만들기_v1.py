def solution(A,B):
    A.sort()
    B.sort(reverse= True)

    ans = 0
    for a, b in zip(A, B):
        ans += a * b
    
    return ans