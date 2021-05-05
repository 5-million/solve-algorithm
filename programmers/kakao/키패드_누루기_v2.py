KEYPAD = {
    1: (0, 0), 2: (0, 1), 3: (0, 2),
    4: (1, 0), 5: (1, 1), 6: (1, 2),
    7: (2, 0), 8: (2, 1), 9: (2, 2),
    '*': (3, 0), 0: (3, 1), '#': (3, 2)
}

def calculate_distance(pos1, pos2):
    return abs(pos2[0] - pos1[0]) + abs(pos2[1] - pos1[1])

def solution(numbers, hand):
    LEFT = set([1, 4, 7])
    RIGHT = set([3, 6, 9])

    left_finger = KEYPAD['*']
    right_finger = KEYPAD['#']

    ans = ''
    for number in numbers:
        number_pos = KEYPAD[number]
        if number in LEFT:
            ans += 'L'
            left_finger = number_pos

        elif number in RIGHT:
            ans += 'R'
            right_finger = number_pos

        else:
            l2n = calculate_distance(left_finger, number_pos)
            r2n = calculate_distance(right_finger, number_pos)

            if l2n < r2n:
                ans += 'L'
                left_finger = number_pos
            elif r2n < l2n:
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