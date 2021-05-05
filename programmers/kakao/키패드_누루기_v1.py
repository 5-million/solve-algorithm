from collections import deque

KEYPAD = [[0] * 3 for _ in range(4)]
init = 1
for i in range(3):
    for j in range(3):
        KEYPAD[i][j] = init
        init += 1

KEYPAD[3][0] = -2
KEYPAD[3][1] = 0
KEYPAD[3][2] = -1

def get_number_pos(number):
    for y in range(4):
        for x in range(3):
            if KEYPAD[y][x] == number:
                return [y, x]

def get_nearest_finger(target, lf, rf):
    qu = deque()
    visit = [[False] * 3 for _ in range(4)]
    visit[target[0]][target[1]] = True

    WAYS = [[-1, 0], [0, 1], [1, 0], [0, -1]] # up right down left

    qu.append(target)
    dist = 0
    ret = [0, 0] # distance [left, right]
    while qu:
        dist += 1
        qu_len = len(qu)

        for _ in range(qu_len):
            y, x = qu.popleft()
            if [y, x] == lf: ret[0] = dist
            elif [y, x] == rf: ret[1] = dist

            if ret[0] and ret[1]: return ret

            for wy, wx in WAYS:
                ny = y + wy
                nx = x + wx

                if 0 <= ny < 4 and 0 <= nx < 3 and not visit[ny][nx]:
                    visit[ny][nx] = True
                    qu.append([ny, nx])

def solution(numbers, hand):
    LEFT = set([1,4,7])
    RIGHT = set([3,6,9])

    left_finger = [3, 0]
    right_finger = [3, 2]

    ans = ''
    for number in numbers:
        number_pos = get_number_pos(number)
        if number in LEFT:
            ans += 'L'
            left_finger = number_pos

        elif number in RIGHT:
            ans += 'R'
            right_finger = number_pos

        else:
            dist_from_left, dist_from_right = get_nearest_finger(number_pos, left_finger, right_finger)
            if dist_from_left < dist_from_right:
                ans += 'L'
                left_finger = number_pos
            elif dist_from_right < dist_from_left:
                ans += 'R'
                right_finger = number_pos
            else:
                if hand == 'left':
                    ans += 'L'
                    left_finger = number_pos
                else:
                    ans += 'R'
                    right_finger = number_pos
    
    return ans

print("LRLLLRLLRRL", solution([1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5], 'right'))
print("LRLLRRLLLRR", solution([7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2], 'left'))
print("LLRLLRLLRL", solution([1, 2, 3, 4, 5, 6, 7, 8, 9, 0], 'right'))