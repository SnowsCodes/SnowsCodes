function conwayGOL () {
    
    var xLower = -2; 
    var xUpper = 2; 
    var yLower = -2; 
    var yUpper = 2; 
    var coords = {
         "2": {"-2": 0, "-1": 1, "0": 0, "1": 0, "2": 0}, 
         "1": {"-2": 0, "-1": 0, "0": 0, "1": 0, "2": 0}, 
         "0": {"-2": 0, "-1": 0, "0": 1, "1": 0, "2": 0}, 
        "-1": {"-2": 0, "-1": 0, "0": 0, "1": 0, "2": 0}, 
        "-2": {"-2": 1, "-1": 1, "0": 0, "1": 0, "2": 0}, 
    }
    
    //for my convinience, delete later
    /*coords = {
         "2": {"-2": 0, "-1": 1, "0": 2, "1": 3, "2": 4}, 
         "1": {"-2": 5, "-1": 6, "0": 7, "1": 8, "2": 9}, 
         "0": {"-2": 10, "-1": 11, "0": 12, "1": 13, "2": 14}, 
        "-1": {"-2": 15, "-1": 16, "0": 17, "1": 18, "2": 19}, 
        "-2": {"-2": 20, "-1": 21, "0": 22, "1": 23, "2": 24}, 
    }*/
    
    var newCoords = {
        "-2": {"-2": 0, "-1": 0, "0": 0, "1": 0, "2": 0}, 
        "-1": {"-2": 0, "-1": 0, "0": 0, "1": 0, "2": 0}, 
         "0": {"-2": 0, "-1": 0, "0": 0, "1": 0, "2": 0}, 
         "1": {"-2": 0, "-1": 0, "0": 0, "1": 0, "2": 0}, 
         "2": {"-2": 0, "-1": 0, "0": 0, "1": 0, "2": 0}, 
    }
    
    function setNewCoords () {
        printCoords(); 
        //check if there are any 1's at the highest y value (top row)
        //var index = Object.getOwnPropertyNames(coords); 
        console.log("yUpper before: " + yUpper);
        //if any of the values at the top row is 1, increase the upper bound of y
        for (var i = xLower; i <= xUpper; i++) {
            console.log("coords: " + coords[i][yUpper] + "  yUpper: " + yUpper);
            console.log(coords[i]);
            if (coords[i][yUpper] != 0) {
                yUpper++; 
            }
        }
        console.log("yUpper after: " + yUpper); 
        
        //while ()
    }
    
    function printCoords() {
        console.log("Value in coords: ");
        for (var i = xUpper; i >= xLower; i--) {
            var row = ""; 
            for (var j = yLower; j <= yUpper; j++) {
                row += coords[i][j];
            }
            console.log(row); 
        }
        console.log(); 
    }
    
    function printNewCoords() {
        console.log("Value in newCoords: ");
        for (var i = xUpper; i >= xLower; i--) {
            var row = ""; 
            for (var j = yLower; j <= yUpper; j++) {
                row += newCoords[i][j];
            }
            console.log(row); 
        }
        console.log(); 
    }
    
    setNewCoords(); 
    
    /*function next (x, y) {
        
    }*/
    
}


conwayGOL(); 

/*var test = {
    "1": 0, 
    "2": 1, 
}

console.log(test[0])
console.log(test[0] == 1 || test[0] == 0)*/
