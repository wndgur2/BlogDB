initCost, macCost, profit = list(map(int, input().split()))
if(profit == macCost):
    result = -1
else:
    result = int(initCost/(profit-macCost))+1
if(result <= 0):
    result = -1
print(result)
