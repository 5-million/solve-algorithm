from itertools import permutations

def calculate(priority, expression):

    for prior in priority:
        temp = []
        index = 0
        while index < len(expression):
            exp = expression[index]

            if exp == prior:
                target = eval(temp.pop() + exp + expression[index+1])
                temp.append(str(target))
                index += 2
            else:
                temp.append(exp)
                index += 1
        
        expression = temp[:]
    
    return abs(int(expression[0]))


def solution(expression):
    OPERATOR = ('*', '+', '-')

    qu = []
    num = ''
    for exp in expression:
        if exp in OPERATOR:
            qu += [num, exp]
            num = ''
        else: num += exp
    qu.append(num)
    
    ans = 0
    for per in permutations(OPERATOR, 3):
        ans = max(ans, calculate(per, qu[:]))
    
    return ans