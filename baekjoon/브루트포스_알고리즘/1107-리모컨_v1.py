# 1192ms

target  = input()
m = int(input())
fail = list(input()) if m else []

BUTTON = ['0', '1', '2', '3', '4' ,'5', '6', '7', '8', '9']

def dfs(channel, count):
    if channel == target: return count
    if len(channel) > len(target): return float('inf')

    ret = float('inf')
    for btn in BUTTON:
        if btn in fail: continue
        nxt = channel + btn
        ret = min(ret, count + abs(int(target) - int(nxt)) + 1)
        ret = min(ret, dfs(nxt, count + 1))
    
    return ret

print(min(dfs("", 0), abs(int(target)-100)))