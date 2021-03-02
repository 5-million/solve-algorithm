def solution(board, moves):
    st = []
    ans = 0

    for m in moves:
        for f in board:
            sel = f[m-1]
            if sel != 0:
                f[m-1] = 0
                break
        
        if sel == 0:
            continue
        if not st:
            st.append(sel)
        else:
            if st[-1] == sel:
                st.pop()
                ans += 2
            else:
                st.append(sel)
    return ans