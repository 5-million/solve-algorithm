from collections import deque

f, s, g, u, d = map(int, input().split())

def get_push_count():
    qu = deque()
    visit = [False] * (f + 1)

    qu.append(s)

    push_count = -1
    while qu:
        qu_len = len(qu)
        push_count += 1
        for _ in range(qu_len):
            here = qu.popleft()

            if here == g:
                return push_count

            nxt_u = here + u
            nxt_d = here - d

            if nxt_u <= f and not visit[nxt_u]:
                visit[nxt_u] = True
                qu.append(nxt_u)
            
            if nxt_d > 0 and not visit[nxt_d]:
                visit[nxt_d] = True
                qu.append(nxt_d)
    
    return 'use the stairs'

print(get_push_count())