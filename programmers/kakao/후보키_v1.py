from itertools import combinations

def is_candidate_key(relation, keys):
    d = set()
    for t in relation:
        data = ""
        for key in keys:
            data += t[key-1]
        
        if data in d:
            return False
        else: d.add(data)
    
    return True

def solution(relation):
    candidate_key = []
    col_len = len(relation[0])
    col = [key for key in range(1, col_len+1)]

    for r in range(1, col_len+1):
        for keys in combinations(col, r):
            for key in candidate_key:
                count = 0
                for k in key:
                    if k in keys:
                        count += 1
                
                if count == len(key):
                    break
            else:
                if is_candidate_key(relation, keys):
                    candidate_key.append(keys)
    
    return len(candidate_key)

print('answer: 2', solution([["100","ryan","music","2"],["200","apeach","math","2"],["300","tube","computer","3"],["400","con","computer","4"],["500","muzi","music","3"],["600","apeach","music","2"]]))