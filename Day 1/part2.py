import math

data = open("input.txt", "r")
data = data.read()

dataList = data.split("\n")
totalFuel = 0

for x in range(0, len(dataList)):
    startFuel = math.floor(int(dataList[x]) / 3) - 2
    currentFuel = startFuel

    while math.floor(currentFuel / 3) != 0:
        currentFuel = math.floor(int(currentFuel) / 3) - 2
        if currentFuel < 0:
            currentFuel = 0
        startFuel = currentFuel + startFuel        

    totalFuel = totalFuel + startFuel

print("Your answer is... " + str(totalFuel))