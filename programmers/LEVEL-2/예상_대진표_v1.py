def solution(n,a,b):
    units = []
    unit = 1
    while unit < n:
        unit *= 2
        units.append(unit)
    
    for idx, unit in enumerate(units):
        for start in range(1, n+1, unit):
            end = start + unit
            if start <= a < end and start <= b < end:
                return idx + 1

print('answer: 3', solution(8, 4, 7))