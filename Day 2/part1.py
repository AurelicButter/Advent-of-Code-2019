data = open("input.txt", "r")
data = data.read().split(",")

check = True
index = 0

while check is True:
    section = data[index]
    firstNum = int(data[int(data[index + 1])])
    secondNum = int(data[int(data[index + 2])])

    if section == "1":
        total = firstNum + secondNum

    if section == "2":
        total = firstNum * secondNum

    data[int(data[index + 3])] = str(total)

    if data[index + 4] == "99":
        check = False

    index = index + 4

print("Your answer is... " + data[0])