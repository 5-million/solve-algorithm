from collections import deque

n, m = map(int, input().split()) # n: 세로, m: 가로
# .: 빈칸, #: 장애물 또는 벽, o: 구멍, R: 빨간 구슬, B: 파란 구슬
board = []
red, blue = [], []
for col in range(n):
    row = list(input())
    board.append(row)
    if 'R' in row:
        red = [col, row.index('R')]
        board[red[0]][red[1]] = '.'
    if 'B' in row:
        blue = [col, row.index('B')]
        board[blue[0]][blue[1]] = '.'

WAYS = [[-1, 0], [0, 1], [1, 0], [0, -1]] # 위 오른쪽 아래 왼쪽

def move(ball, way):
    q = deque()
    q.append(ball)

    count = -1
    while q:
        y, x = q.popleft()
        dy, dx = way

        ny, nx = y + dy, x + dx

        if 0 <= ny < n and 0 <= nx < m:
            nxt = board[ny][nx]
            if nxt == 'O': return [True, count]
            if nxt == '.': q.append([ny, nx])
    
        count += 1
    
    return [[y, x], count]

def solution():
    ans = 0
    deq = deque()

    deq.append([red, blue])
    visit = [[red, blue]]

    while deq and ans < 10:
        ans += 1
        len_deq = len(deq)

        for _ in range(len_deq):
            now_red, now_blue = deq.popleft()

            for i, way in enumerate(WAYS):
                nxt_red, red_move_count = move(now_red, way)
                nxt_blue, blue_move_count = move(now_blue, way)

                if nxt_blue == True:
                    continue
                elif nxt_red == True:
                    return ans

                # 빨간 공과 파란 공의 위치가 같으면 이동한 거리가 먼 공을 이동한 반대방향으로 한칸 이동
                if nxt_red == nxt_blue:
                    if red_move_count > blue_move_count:
                        nxt_red[0] += WAYS[i-2][0]
                        nxt_red[1] += WAYS[i-2][1]
                    else:
                        nxt_blue[0] += WAYS[i-2][0]
                        nxt_blue[1] += WAYS[i-2][1]
                
                if [nxt_red, nxt_blue] in visit:
                    continue                
                else: visit.append([nxt_red, nxt_blue])

                deq.append([nxt_red, nxt_blue])
        
    return -1

print(solution())