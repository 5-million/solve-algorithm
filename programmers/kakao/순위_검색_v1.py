def make_all_case(info):
    inf = info.split()
    score = int(inf.pop())
    
    ret = ['']
    for i in inf:
        temp = []
        for r in ret:
            temp.append(r + i)
            temp.append(r + '-')
        ret = temp
    
    return ret, score

def getLowerCount(value, score):
    start = 0
    end = len(value) - 1
    
    count = 0
    while start <= end:
        mid = (start + end) // 2
        
        if value[mid] >= score:
            end = mid - 1
        else:
            count = mid + 1
            start = mid + 1
    
    return count

def solution(infos, querys):
    info_dict = {}
    ans = []
    for info in infos:
        all_case, score = make_all_case(info)
        for case in all_case:
            if case not in info_dict.keys():
                info_dict[case] = [score]
            else:
                info_dict[case].append(score)
    
    for key in info_dict.keys():
        info_dict[key].sort()
    
    for query in querys:
        query = query.split(" and ")
        food, score = query.pop().split()
        query.append(food)
        score = int(score)
        
        key = ''.join(query)
        if key not in info_dict.keys():
            ans.append(0)
            continue
            
        value = info_dict[key]
        
        ans.append(len(value) - getLowerCount(value, score))
        
    return ans