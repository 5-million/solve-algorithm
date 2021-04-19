import re

def solution(new_id: str):
    new_id = new_id.lower()
    new_id = re.sub('[^a-zA-Z0-9_\-.]', "", new_id)
    new_id = re.sub('\.{2,}', ".", new_id)
    new_id = re.sub('^[.]|[.]$', "", new_id) # ^[.] : 첫 글자가 .이면 // [.]$ : 마지막 글자가 .이면

    if not new_id: new_id = 'a'

    if len(new_id) > 15:
        new_id = re.sub('^[.]|[.]$', "", new_id[:15])

    if len(new_id) < 3:
        new_id += new_id[-1] * (3-len(new_id))

    return new_id

print(solution("...!@BaT#*..y.abcdefghijklm"), "bat.y.abcdefghi")
print(solution("z-+.^."), "z--")
print(solution("=.="), "aaa")
print(solution("123_.def"), "123_.def")
print(solution("abcdefghijklmn.p"), "abcdefghijklmn")