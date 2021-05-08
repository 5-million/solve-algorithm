def solution(gems):
    set_gems = set(gems)
    len_gems = len(gems)

    gems_count = {gems[0] : 1}

    start, end = 0, len_gems
    l, r = 0, 0
    while l < len_gems and r < len_gems:
        if len(gems_count) == len(set_gems):   
            if r - l < end - start:
                end = r
                start = l

            gem = gems[l]
            gems_count[gem] -= 1
            if gems_count[gem] == 0:
                gems_count.pop(gem)
            
            l += 1

        else:
            r += 1
            if r == len_gems:
                break

            gem = gems[r]
            if gem not in gems_count.keys():
                gems_count[gem] = 1
            else: gems_count[gem] += 1         
    
    return [start+1, end+1]

print([3, 7], solution(["DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"]))
print([1, 3], solution(["AA", "AB", "AC", "AA", "AC"]))
print([1, 1], solution(["XYZ", "XYZ", "XYZ"]))
print([1, 5], solution(["ZZZ", "YYY", "NNNN", "YYY", "BBB"]))
print([2, 3], solution(["A", "A", "B"]))