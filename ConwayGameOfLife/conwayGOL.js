function conwayGOL () {
    
    var xLower = -2; 
    var xUpper = 2; 
    var yLower = -2; 
    var yUpper = 2; 
    var coords = {
         "2": {"-2": 0, "-1": 0, "0": 0, "1": 0, "2": 0}, 
         "1": {"-2": 0, "-1": 0, "0": 0, "1": 1, "2": 0}, 
         "0": {"-2": 0, "-1": 1, "0": 0, "1": 1, "2": 0}, 
        "-1": {"-2": 0, "-1": 0, "0": 1, "1": 1, "2": 0}, 
        "-2": {"-2": 0, "-1": 0, "0": 0, "1": 0, "2": 0}, 
    }
    
    //for my convinience, delete later
    /*coords = {
         "2": {"-2":  0, "-1":  1, "0":  2, "1":  3, "2":  4}, 
         "1": {"-2":  5, "-1":  6, "0":  7, "1":  8, "2":  9}, 
         "0": {"-2": 10, "-1": 11, "0": 12, "1": 13, "2": 14}, 
        "-1": {"-2": 15, "-1": 16, "0": 17, "1": 18, "2": 19}, 
        "-2": {"-2": 20, "-1": 21, "0": 22, "1": 23, "2": 24}, 
    }*/
    
    var newCoords = {
    }
    
    function getCoordsVal (x, y) {
        return coords[y][x];
    }
    
    function getNCoordsVal (x, y) {
        return newCoords[y][x];
    }
    
    function setNewCoords () {
        
        //c stands for change
        var cXUpper = false; 
        var cXLower = false; 
        var cYUpper = false; 
        var cYLower = false; 
        
        //the lower and upper bounds of values that have 8 neighbors
        var xUp = xUpper-1; 
        var xLow = xLower+1;
        var yUp = yUpper-1; 
        var yLow = yLower+1; 
        
        
        console.log("\nyUpper: " + yUpper + "\nyLower: " + yLower + "\nxLower: " + xLower + "\nxUpper: " + xUpper); 
        
        //check if it needs to expanded, if yes, expand it
        //check y upper bound
        for (var i = xLower; i <= xUpper; i++) {
            if (getCoordsVal(i, yUpper) != 0) {
                cYUpper = true; 
                yUp++; 
                break;
            }
        }
        
        //check y lower bound
        for (var i = xLower; i <= xUpper; i++) {
            if (getCoordsVal(i, yLower) != 0) {
                cYLower = true; 
                yLow--; 
                break;
            }
        }
        
        //check x lower bound
        for (var i = yUpper; i >= yLower; i--) {
            if (getCoordsVal(xLower, i) != 0) {
                cXLower = true; 
                xLow--; 
                break; 
            }
        }
        
        //check x upper bound
        for (var i = yUpper; i >= yLower; i--) {
            if (getCoordsVal(xUpper, i) != 0) {
                cXUpper = true; 
                xUp++; 
                break; 
            }
        }
        
        //change upper and lower bound based on the true and false value that was determined in the for loops
        //if it gets changed, create the row/column that goes with it
        if (cYUpper) {
            yUpper++; 
            coords[yUpper] = {}; 
            for (var i = xLower; i <= xUpper; i++) {
                coords[yUpper][i] = 0; 
            }
        }
        if (cXUpper) {
            xUpper++; 
            coords[xUpper] = {}; 
            for (var i = yLower; i <= yUpper; i++) {
                coords[i][xUpper] = 0; 
            }
        }
        if (cYLower) {
            yLower--; 
            coords[yLower] = {}; 
            for (var i = xLower; i <= xUpper; i++) {
                coords[yLower][i] = 0; 
            }
        }
        if (cXLower) {
            xLower--; 
            coords[xLower] = {}; 
            for (var i = yLower; i <= yUpper; i++) {
                coords[i][xLower] = 0; 
            }
        }
        console.log("\nyUpper: " + yUpper + "\nyLower: " + yLower + "\nxLower: " + xLower + "\nxUpper: " + xUpper); 
        //console.log("\nyUp: " + yUp + "\nyLow: " + yLow + "\nxLow: " + xLow + "\nxUp: " + xUp); 
        
        
        
        //update the values
        
        //create the new y values for newCoords
        for (var i = yLower; i <= yUpper; i++) {
            newCoords[i] = {};
            for (var j = xLower; j <= xUpper; j++) {
                newCoords[i][j] = "-"; 
            }
        }
        
        
        //update values for values not on the border
        for (var i = yUp; i >= yLow; i--) {
            for (var j = xLow; j <= xUp; j++) {
                var numFilled = count(j, i);
                if (numFilled == 3) {
                    newCoords[i][j] = 1; 
                } else if (numFilled == 2 && coords[i][j] == 1) {
                    newCoords[i][j] = 1; 
                } else {
                    newCoords[i][j] = 0; 
                }
            }
        }
        
        //printCoords(); 
        //console.log(); 
        
        
        //update values on the border 
        //value on the corners is always going to be 0
        newCoords[yUpper][xLower] = 0; 
        newCoords[yLower][xLower] = 0; 
        newCoords[yUpper][xUpper] = 0; 
        newCoords[yLower][xUpper] = 0; 
        
        //value on the top row
        for (var i = xLow; i <= xUp; i++) {
            if (getCoordsVal(i-1, yUp) == 1 && getCoordsVal(i, yUp) == 1 && getCoordsVal(i+1, yUp) == 1) {
                newCoords[yUpper][i] = 1;
            } else {
                newCoords[yUpper][i] = 0;
            }
        }
        
        //value on the bottom row
        for (var i = xLow; i <= xUp; i++) {
            if (getCoordsVal(i-1, yLow) == 1 && getCoordsVal(i, yLow) == 1 && getCoordsVal(i+1, yLow) == 1) {
                newCoords[yLower][i] = 1;
            } else {
                newCoords[yLower][i] = 0;
            }
        }
        
        //value on the left column
        for (var i = yUp; i >= yLow; i--) {
            if (coords[i+1][xLow] == 1 && coords[i][xLow] == 1 && coords[i-1][xLow] == 1)  {
                newCoords[i][xLower] = 1; 
            } else {
                newCoords[i][xLower] = 0; 
            }
        }
        
        //value on the right row
        for (var i = yUp; i >= yLow; i--) {
            if (coords[i+1][xUp] == 1 && coords[i][xUp] == 1 && coords[i-1][xUp] == 1)  {
                newCoords[i][xUpper] = 1; 
            } else {
                newCoords[i][xUpper] = 0; 
            }
        }
    }
    
    //count the number of neighbors that are filled (aka value of 1)
    function count (x, y) {
        var count = 0; 
        if (coords[y+1][x-1] == 1) {
            count++; 
        }
        if (coords[y+1][x] == 1) {
            count++; 
        }
        if (coords[y+1][x+1] == 1) {
            count++; 
        }
        if (coords[y][x-1] == 1) {
            count++; 
        }
        if (coords[y][x+1] == 1) {
            count++; 
        }
        if (coords[y-1][x-1] == 1) {
            count++; 
        }
        if (coords[y-1][x] == 1) {
            count++; 
        }
        if (coords[y-1][x+1] == 1) {
            count++; 
        }
        return count; 
    }
    
    function printCoords () {
        for (var y = yUpper; y >= yLower; y--) {
            var row = ""; 
            for (var x = xLower; x <= xUpper; x++) {
                row += getCoordsVal(x, y);
            }
            console.log(row); 
        }
    }
    
    function printFullCoords (lim) {
        for (var i = lim * -1; i <= lim; i++) {
            var row = ""; 
            for (var j = lim * -1; j <= lim; j++) {
                if (i < yLower || i > yUpper || j < xLower || j > xUpper) {
                    row += "0 "
                } else {
                    row += coords[i][j] + " ";
                }
            }
            console.log(row); 
        }
    }
    
    function printNewCoords () {
        for (var y = yUpper; y >= yLower; y--) {
            var row = ""; 
            for (var x = xLower; x <= xUpper; x++) {
                row += getNCoordsVal(x, y);
            }
            console.log(row); 
        }
    }
    
    function updateCoords () {
        for (var i = yUpper; i >= yLower; i--) {
            for (var j = xLower; j <= xUpper; j++) {
                coords[i][j] = newCoords[i][j];
            }
        }
    }
    
    setNewCoords(); 
    updateCoords(); 
    printFullCoords(10); 
    setNewCoords(); 
    updateCoords(); 
    printFullCoords(10); 
    
    
}


conwayGOL(); 

/*var test = {
    "1": {}, 
    "2": 1, 
}

console.log(typeof test[1]);
console.log(typeof test[0]);

/*var test = {
    "1": 0, 
    "2": 1, 
}

console.log(test[0])
console.log(test[0] == 1 || test[0] == 0)*/



