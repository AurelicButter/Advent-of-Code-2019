const fs = require("fs");

var values = fs.readFileSync('input.txt', 'utf8').split("\r\n"); //Reads the values

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

var objDict = {};
var noParent = [];

for (var x = 0; x < values.length; x++) {
    var [planet, orbit] = values[x].split(")"); //Value is as followed: Planet ) Orbiting

    if (!objDict[planet]) {
        var item = objInit(planet, orbit);
        objDict[planet] = item;
        noParent.push(planet);
    } else {
        if (objDict[planet].orbitial[0] === null) {
            objDict[planet].orbitial = [orbit];
        } else {
            objDict[planet].orbitial.push(orbit);
        }        
    }

    if (!objDict[orbit]) {
        var item = objInit(orbit, null, planet);
        objDict[orbit] = item;
    } else {
        var index = noParent.indexOf(orbit);
        if (index > -1) { noParent.splice(index, 1); }
    }
}

var totalCount = 0;

for (var z = 0; z < noParent.length; z++) {
    var currPlanet = objDict[noParent[z]];
    countParentPath(currPlanet);
}

var keys = Object.keys(objDict);

for (var y = 0; y < keys.length; y++) {
    totalCount = totalCount + objDict[keys[y]].count;
}

console.log("Your answer is... " + totalCount);