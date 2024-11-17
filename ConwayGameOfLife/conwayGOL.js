function conwayGOL () {
    
    var xLower = -2; 
    var xUpper = 2; 
    var yLower = -2; 
    var yUpper = 2; 
    var coords = {
         "2": {"-2": 0, "-1": 0, "0": 0, "1": 0, "2": 0}, 
         "1": {"-2": 1, "-1": 0, "0": 0, "1": 0, "2": 0}, 
         "0": {"-2": 0, "-1": 0, "0": 1, "1": 0, "2": 0}, 
        "-1": {"-2": 0, "-1": 0, "0": 0, "1": 0, "2": 0}, 
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
        
        //check if it needs to expanded, if yes, expand it
        //check y upper bound
        console.log("yUpper: " + yUpper);
        for (var i = xLower; i <= xUpper; i++) {
            if (getCoordsVal(i, yUpper) != 0) {
                cYUpper = true; 
                yUp++; 
                break;
            }
        }
        
        //check y lower bound
        console.log("yLower: " + yLower);
        for (var i = xLower; i <= xUpper; i++) {
            if (getCoordsVal(i, yLower) != 0) {
                cYLower = true; 
                yLow--; 
                break;
            }
        }
        
        //check x lower bound
        console.log("xLower: " + xLower); 
        for (var i = yUpper; i >= yLower; i--) {
            if (getCoordsVal(xLower, i) != 0) {
                cXLower = true; 
                xLow--; 
                break; 
            }
        }
        
        //check x upper bound
        console.log("xUpper: " + xUpper);
        for (var i = yUpper; i >= yLower; i--) {
            if (getCoordsVal(xUpper, i) != 0) {
                cXUpper = false; 
                xUp++; 
                break; 
            }
        }
        
        //change upper and lower bound based on the true and false value that was determined in the for loops
        //if it gets changed, create the row/column that goes with it
        printCoords(); 
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
        printCoords(); 
        console.log("\nyUpper: " + yUpper + "\nyLower: " + yLower + "\nxLower: " + xLower + "\nxUpper: " + xUpper); 
        
        
        
        //create the new values
        //create new values for values not on the border
        
        
        //create values for values on the border
        //value on the top right
        //value on the top left
        //value on the bottom right
        //value on the bottom left
        //value on the top
        //value on the bottom
        //value on the left
        //value on the right
    }
    
    //count the number of neighbors that are filled (aka value of 1)
    function count (x, y) {
        
    }
    
    function printCoords () {
    }
    
    function printNewCoords () {
    }
    
    setNewCoords(); 
    
    function next (x, y) {
        
    }
    
}


conwayGOL(); 

/*var test = {
    "1": 0, 
    "2": 1, 
}

console.log(test[0])
console.log(test[0] == 1 || test[0] == 0)*/
