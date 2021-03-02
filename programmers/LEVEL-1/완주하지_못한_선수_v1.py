def solution(participant, completion):
    dic = {}
    for parti in participant:
        if parti in dic:
            dic[parti] += 1
        else:
            dic[parti] = 1
    
    for comp in completion:
        dic[comp] -= 1

    for d in dic.keys():
        if dic[d] == 1:
            return d