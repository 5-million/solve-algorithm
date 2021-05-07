exist_combi = []

def get_combination_count(combi, values, index):
    if index == len(values):
        temp = sorted(combi)
        
        if temp not in exist_combi:
            exist_combi.append(temp[:])
            return 1
        else: return 0
    else:
        ret = 0
        for user_id in values[index]:
            if user_id not in combi:
                combi.append(user_id)
                ret += get_combination_count(combi, values, index+1)
                combi.pop()

        return ret

def is_match(user_id, banned_id):
    if len(user_id) != len(banned_id):
        return False
    else:
        for uid, bid in zip(user_id, banned_id):
            if uid == bid or bid == '*':
                continue
            else: return False
        
        return True

def solution(user_id, banned_id):
    case = {}
    for index, bid in enumerate(banned_id):
        case[index] = []
        for uid in user_id:
            if is_match(uid, bid):
                case[index].append(uid)

    ans = get_combination_count([], list(case.values()), 0)
    return ans

print(2, \
    solution(["frodo", "fradi", "crodo", "abc123", "frodoc"], ["fr*d*", "abc1**"]))
print(2, \
    solution(["frodo", "fradi", "crodo", "abc123", "frodoc"], ["*rodo", "*rodo", "******"]))
print(3, \
    solution(["frodo", "fradi", "crodo", "abc123", "frodoc"], ["fr*d*", "*rodo", "******", "******"]))