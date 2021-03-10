def solution(genres, plays):
    play_count = {}
    genr = {}
    for id, (g, p) in enumerate(zip(genres, plays)):
        if g not in play_count.keys():
            play_count[g] = p
        else: play_count[g] += p

        if g not in genr.keys():
            genr[g] = [[p, id]]
        else: genr[g].append([p, id])

    play_count = sorted(play_count.items(), key= lambda x: x[1], reverse=True)

    ans = []
    for gen, _ in play_count:
        gen = sorted(genr[gen], key = lambda x: (x[0], -x[1]), reverse=True)
        ans += [id for _, id in gen][:2]
    
    return ans