def getSlicedTree(trees, cut):
    length_tree = 0
    for tree in trees:
        if tree > cut:
            length_tree += tree - cut
    
    return length_tree

def solution():
    n, m = map(int, input().split())
    trees = list(map(int, input().split()))
    trees.sort()
    start = 0
    end = trees[-1]

    cut = 0
    while start <= end:
        cut = (start + end) // 2
        sliced_tree = getSlicedTree(trees, cut)

        if sliced_tree < m: end = cut - 1
        else: start = cut + 1
    
    if getSlicedTree(trees, cut) >= m: return cut
    else: return cut - 1

print(solution())