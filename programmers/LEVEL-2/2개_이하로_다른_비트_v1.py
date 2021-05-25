# 낮은 수를 우선으로 00 → 01, 01 → 10, 10 → 11로 변환
# 변하는 수가 없다면 가장 높은 비트를 0으로, 비트 1을 추가 ex) 111 → 1011
def f(number):
    if number % 2 == 0: return number+1
    
    binary = list(bin(number))[2:]
    binary.reverse()

    for i in range(len(binary)-1):
        if binary[i] == '0':
            binary[i] = '1'
            break
        else:
            if binary[i+1] == '0':
                binary[i:i+2] = ['0', '1']
                break
    else:
        binary[-1] = '0'
        binary.append('1')

    binary.reverse()
    return int(''.join(binary), 2)

def solution(numbers):
    return [f(number) for number in numbers]
    
print('result: [3, 11]', solution([2, 7]))