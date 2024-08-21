s = input()
alps = [0]*26
for c in s:
    alps[ord(c.lower())-97]+=1

res = max(alps)
amount = 0
for i in alps:
    if(i==res):
        amount += 1
if(amount>1):
    print('?')
else:
    print(chr(alps.index(res)+97).upper())
