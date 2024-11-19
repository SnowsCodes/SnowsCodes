var coords = {
    "2": {"-2": 0, "-1": 0, "0": 0, "1": 0, "2": 0}, 
    "1": {"-2": 0, "-1": 0, "0": 1, "1": 1, "2": 0}, 
    "0": {"-2": 0, "-1": 1, "0": 1, "1": 0, "2": 0}, 
   "-1": {"-2": 0, "-1": 0, "0": 1, "1": 0, "2": 0}, 
   "-2": {"-2": 0, "-1": 0, "0": 0, "1": 0, "2": 0}, 
}
    
function conwayGOL (dimensions) {
    
    var xLower = -2; 
    var xUpper = 2; 
    var yLower = -2; 
    var yUpper = 2; 
    
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
        
        //check if it needs to expanded, if yes, expand it
        //check y upper bound
        for (var i = xLower; i <= xUpper; i++) {
            if (getCoordsVal(i, yUpper) != 0) {
                cYUpper = true; 
                break;
            }
        }
        
        //check y lower bound
        for (var i = xLower; i <= xUpper; i++) {
            if (getCoordsVal(i, yLower) != 0) {
                cYLower = true; 
                break;
            }
        }
        
        //check x lower bound
        for (var i = yUpper; i >= yLower; i--) {
            if (getCoordsVal(xLower, i) != 0) {
                cXLower = true; 
                break; 
            }
        }
        
        //check x upper bound
        for (var i = yUpper; i >= yLower; i--) {
            if (getCoordsVal(xUpper, i) != 0) {
                cXUpper = true;
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
        
        
        
        //update the values
        
        //create the new y values for newCoords
        for (var i = yLower; i <= yUpper; i++) {
            newCoords[i] = {};
            for (var j = xLower; j <= xUpper; j++) {
                newCoords[i][j] = "-"; 
            }
        }
        
        
        //update values for values not on the border
        for (var i = yUpper-1; i >= yLower+1; i--) {
            for (var j = xLower+1; j <= xUpper-1; j++) {
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
        
        
        //update values on the border 
        //value on the corners is always going to be 0
        newCoords[yUpper][xLower] = 0; 
        newCoords[yLower][xLower] = 0; 
        newCoords[yUpper][xUpper] = 0; 
        newCoords[yLower][xUpper] = 0; 
        
        //value on the top row
        for (var i = xLower+1; i <= xUpper-1; i++) {
            if (getCoordsVal(i-1, yUpper-1) == 1 && getCoordsVal(i, yUpper-1) == 1 && getCoordsVal(i+1, yUpper-1) == 1) {
                newCoords[yUpper][i] = 1;
            } else {
                newCoords[yUpper][i] = 0;
            }
        }
        
        //value on the bottom row
        for (var i = xLower+1; i <= xUpper-1; i++) {
            if (getCoordsVal(i-1, yLower+1) == 1 && getCoordsVal(i, yLower+1) == 1 && getCoordsVal(i+1, yLower+1) == 1) {
                newCoords[yLower][i] = 1;
            } else {
                newCoords[yLower][i] = 0;
            }
        }
        
        //value on the left column
        for (var i = yUpper-1; i >= yLower+1; i--) {
            if (coords[i+1][xLower+1] == 1 && coords[i][xLower+1] == 1 && coords[i-1][xLower+1] == 1)  {
                newCoords[i][xLower] = 1; 
            } else {
                newCoords[i][xLower] = 0; 
            }
        }
        
        //value on the right row
        for (var i = yUpper-1; i >= yLower+1; i--) {
            if (coords[i+1][xUpper-1] == 1 && coords[i][xUpper-1] == 1 && coords[i-1][xUpper-1] == 1)  {
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
        for (var i = lim; i >= lim * -1; i--) {
            var row = ""; 
            for (var j = lim * -1; j <= lim; j++) {
                if (i < yLower || i > yUpper || j < xLower || j > xUpper) {
                    row += "\u2610 "
                } else {
                    if (coords[i][j] == 1) {
                        row += "\u25a0 "
                    } else {
                        row += "\u2610 ";
                    }
                }
            }
            console.log(row); 
        }
        console.log(""); 
    }
    
    function updateCoords () {
        for (var i = yUpper; i >= yLower; i--) {
            for (var j = xLower; j <= xUpper; j++) {
                coords[i][j] = newCoords[i][j];
            }
        }
    }
    
  async function run() {
    var i = 2; 
     while (true) {
       console.clear(); 
       setNewCoords(); 
       updateCoords(); 
       console.log("tick: " + i)
       printFullCoords(dimensions); 
       console.log(); 
       
       await new Promise(resolve => setTimeout(resolve, 100));
       i++; 
     }
  }
  run(); 
}

conwayGOL(24); 
