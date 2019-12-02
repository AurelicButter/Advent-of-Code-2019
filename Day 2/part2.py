def findTotal(z, x, y):
    if z == "1":
        return x + y
    if z == "2":
        return x * y
    else:
        raise Exception("INVALID Z: " + z)

def executeCode(x, y):
    check = True
    index = 0
    dataList = open("input.txt", "r").read().split(",")
    dataList[1] = str(x)
    dataList[2] = str(y)

    while check is True:
        section = dataList[index]
        firstNum = int(dataList[int(dataList[index + 1])])
        secondNum = int(dataList[int(dataList[index + 2])])

        total = findTotal(section, firstNum, secondNum)

        dataList[int(dataList[index + 3])] = str(total)

        if dataList[index + 4] == "99":
            check = False

        index = index + 4

    if dataList[0] == "19690720":
        print("Noun: " + str(x) + " Verb: " + str(y))
        return True
    else:
        return False

for x in range(100):
    for y in range(100):
        result = executeCode(x, y)
        if result is True:
            print("Your answer is... " + str(100 * x + y))
