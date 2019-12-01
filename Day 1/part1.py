import math

data = open("input.txt", "r")
data = data.read()

dataList = data.split("\n")
totalFuel = 0

for x in range(0, len(dataList)):
    totalFuel = math.floor(int(dataList[x]) / 3) - 2 + totalFuel

print("Your answer is...")
print(totalFuel)