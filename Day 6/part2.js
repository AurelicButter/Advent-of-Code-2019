const fs = require("fs");

var values = fs.readFileSync('./input.txt', 'utf8').split("\r\n"); //Reads the values

function objInit(ID, item=null, parent=null) {
    return {
        ID: ID,
        orbitial: [item],
        parent: parent,
        count: null
    }
}

function countParentPath(planet, count=0) {
    if (planet.count == null) { planet.count = count; }
    if (planet.orbitial[0] == null) { return; }
    for (var x = 0; x < planet.orbitial.length; x++) {
        countParentPath(objDict[planet.orbitial[x]], count + 1);
    }
}

let testedKeys = [null];
function findPath(planet, count) {
    if (planet.ID == "COM") { return; }
    if (planet.ID == "SAN") { return count - 2; }
    if (testedKeys.includes(planet.ID)) { return; }

    testedKeys.push(planet.ID);
    var smallestCheck = 10000; //Something really high to get rewritten in the loop.

    var orbitCopy = Array.from(planet.orbitial);
    testedKeys.forEach(e => {
        var index = orbitCopy.indexOf(e);
        if (index > -1) { orbitCopy.splice(index, 1); }
    });

    orbitCopy.push(planet.parent);

    for (var x = 0; x < orbitCopy.length; x++) {
        if (!testedKeys.includes(orbitCopy[x])) {
            check = findPath(objDict[orbitCopy[x]], count + 1);
            if (smallestCheck > check) { smallestCheck = check; }
        }
    }    

    return smallestCheck;
}

var objDict = {};

for (var x = 0; x < values.length; x++) {
    var [planet, orbit] = values[x].split(")"); //Value is as followed: Planet ) Orbiting

    if (!objDict[planet]) {
        var item = objInit(planet, orbit);
        objDict[planet] = item;
    } else {
        if (objDict[planet].orbitial[0] === null) {
            objDict[planet].orbitial = [orbit];
        } else { objDict[planet].orbitial.push(orbit); }       
    }

    if (!objDict[orbit]) {
        var item = objInit(orbit, null, planet);
        objDict[orbit] = item;
    } else { objDict[orbit].parent = planet; }
}

countParentPath(objDict["COM"]);

var totalCount = findPath(objDict["YOU"], 0);

console.log("Your answer is... " + totalCount);