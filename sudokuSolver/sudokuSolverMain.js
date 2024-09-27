//set up sudoku boxes and their solved boxes, 0 means unsolved
{
    var row1 = [0, 0, 0, 0, 0, 0, 0, 0, 0]; 
    var row2 = [0, 0, 0, 0, 0, 0, 0, 0, 0];
    var row3 = [0, 0, 0, 0, 0, 0, 0, 0, 0];
    var row4 = [0, 0, 0, 0, 0, 0, 0, 0, 0];
    var row5 = [0, 0, 0, 0, 0, 0, 0, 0, 0];
    var row6 = [0, 0, 0, 0, 0, 0, 0, 0, 0];
    var row7 = [0, 0, 0, 0, 0, 0, 0, 0, 0];
    var row8 = [0, 0, 0, 0, 0, 0, 0, 0, 0];
    var row9 = [0, 0, 0, 0, 0, 0, 0, 0, 0];
    
    var rows = [row1, row2, row3, row4, row5, row6, row7, row8, row9];
    
    var pos1 = ["", "", "", "", "", "", "", "", ""]; 
    var pos2 = ["", "", "", "", "", "", "", "", ""]; 
    var pos3 = ["", "", "", "", "", "", "", "", ""]; 
    var pos4 = ["", "", "", "", "", "", "", "", ""]; 
    var pos5 = ["", "", "", "", "", "", "", "", ""]; 
    var pos6 = ["", "", "", "", "", "", "", "", ""]; 
    var pos7 = ["", "", "", "", "", "", "", "", ""]; 
    var pos8 = ["", "", "", "", "", "", "", "", ""]; 
    var pos9 = ["", "", "", "", "", "", "", "", ""]; 
    var possibilities = [pos1, pos2, pos3, pos4, pos5, pos6, pos7, pos8, pos9];
}

//to make my life easier
{
    //not in use atm
    var row1 = [0, 6, 9, 1, 7, 0, 0, 0, 0]; 
    var row2 = [0, 0, 0, 6, 0, 0, 3, 0, 0];
    var row3 = [5, 0, 8, 0, 2, 0, 0, 0, 7];
    var row4 = [0, 0, 0, 2, 0, 0, 0, 5, 6];
    var row5 = [6, 0, 1, 0, 0, 0, 2, 3, 0];
    var row6 = [0, 5, 0, 0, 8, 0, 0, 0, 0];
    var row7 = [0, 2, 6, 4, 0, 5, 8, 7, 3];
    var row8 = [8, 1, 4, 7, 0, 2, 0, 6, 9];
    var row9 = [0, 0, 0, 8, 0, 9, 1, 0, 0];
    
    var row1 = [0, 5, 0, 9, 1, 0, 0, 0, 0]; 
    var row2 = [0, 1, 0, 0, 3, 0, 5, 8, 0];
    var row3 = [7, 4, 0, 0, 0, 0, 1, 2, 0];
    var row4 = [4, 3, 0, 0, 0, 9, 0, 0, 7];
    var row5 = [2, 0, 0, 0, 5, 8, 0, 0, 0];
    var row6 = [9, 8, 1, 3, 0, 4, 2, 0, 5];
    var row7 = [0, 0, 3, 0, 6, 5, 0, 7, 2];
    var row8 = [0, 6, 7, 0, 0, 3, 0, 5, 1];
    var row9 = [5, 0, 4, 0, 0, 0, 0, 6, 0];
    
    var rows = [row1, row2, row3, row4, row5, row6, row7, row8, row9];
}

//stands for generate all possibilies of a value

function genCoords (index) {
    var numOfRow = Math.floor(index/9); 
    var numOfColumn = (index%9); 
    return [numOfRow, numOfColumn]
}

function genFromRow (num) {
    //create an array to store unfiltered possibilities
    var unfiltered = [1, 2, 3, 4, 5, 6, 7, 8, 9];
    
    //find the row, column, and box number it is in, where the 0 gives the 0th row, 0th column, and 0th box
    var rowNum = Math.floor(num/9); 
    var columnNum = (num % 9); 
    var boxNum = (3 * Math.floor(rowNum/3)) + Math.floor(columnNum/3); 
    //console.log("num is at " + rowNum + ", " + columnNum + ", " + boxNum);
    
    
    //remove duplicates from the row it is in, which is rows[rowNum]
    for (var i = 0; i < 9; i++) {
        if (rows[rowNum][i] != 0) {
            //find the index of rows[rowNum][i] in unfiltered, and store it in index
            var index = unfiltered.indexOf(rows[rowNum][i]); 
            //remove one element at the index of the variable index
            unfiltered.splice(index, 1);
        }
    }
    
    //remove duplicates from columns
    for (var i = 0; i < 9; i++) {
        if (rows[i][columnNum] != 0) {
            //find the index of rows[i][columnNum] in unfiltered, and store it in index, and if the target doesn't exist, set index as -1
            var index = unfiltered.indexOf(rows[i][columnNum]); 
            
            //if index is more than -1, remove one element at the index of the variable index 
            if (index > -1) {
                unfiltered.splice(index, 1);
            }
        }
    }
    
    //generate "coords" for all the values in a box
    var boxRowNum = []; 
    var boxColumnNum = []; 
    for (var i = 0; i < 9; i++) {
        boxRowNum.push(3*Math.floor(boxNum/3) + Math.floor(i/3));
        boxColumnNum.push(((boxNum*3)%9) + i%3); 
    }
    
    //check for each coord
    for (var i = 0; i < boxRowNum.length; i++) {
        if (rows[boxRowNum[i]][boxColumnNum[i]] != 0) {
            //find the index of rows[i][columnNum] in unfiltered, and store it in index, and if the target doesn't exist, set index as -1
            var index = unfiltered.indexOf(rows[boxRowNum[i]][boxColumnNum[i]]); 
            
            //if index is more than -1, remove one element at the index of the variable index 
            if (index > -1) {
                unfiltered.splice(index, 1);
            }
        }
    }
    
    //if there is only one value left in unfiltered, set that as the value
    //CHECK IF THIS WORKS
    if (unfiltered.length == 1) {
        rows[rowNum][columnNum] = unfiltered[0]; 
    }
    
    //change all elements in unfiltered into a string and add it all together
    var tempString = ""; 
    if (rows[rowNum][columnNum] == 0) {
        for (var i = 0; i < unfiltered.length; i++) {
            tempString += unfiltered[i]; 
        }
    }
    //console.log("tempString is " + tempString); 
    
    possibilities[rowNum][columnNum] = tempString; 
    
}

function mainRep () {
    for(var i = 0; i < 81; i++) {
        var numOfRow = Math.floor(i/9); 
        var numOfColumn = (i%9); 
        if (rows[numOfRow][numOfColumn] == 0) {
            genFromRow(i); 
        }
    }
}

//can delete later, just for better visualization
function visualizePossibilities () {
  var visualize = ""; 
  for (var i = 0; i < 81; i++) {
    if ((possibilities[genCoords(i)[0]][genCoords(i)[1]]).length > 0) {
      visualize += possibilities[genCoords(i)[0]][genCoords(i)[1]] + " ";
    } else {
      visualize += "- "
    }
    
    if (i%9 == 8) {
      console.log(visualize);
      visualize = ""; 
    }
  }
}
function visualizeSolved () {
  var visualize = ""; 
  for (var i = 0; i < 81; i++) {
    visualize += rows[genCoords(i)[0]][genCoords(i)[1]] + " ";
    
    if (i%9 == 8) {
      console.log(visualize);
      visualize = ""; 
    }
  }
}

var complete = false; 
var counter = 0; 
while (!complete && counter < 52) {
    mainRep(); 
    counter++; 
    visualizePossibilities(); 
    
}

if (!complete) {
    var unsolved = []; 
    for (var i = 0; i < 81; i++) {
        var numOfRow = Math.floor(i/9); 
        var numOfColumn = (i%9); 
        if (rows[numOfRow][numOfColumn] == 0) {
            unsolved.push(i); 
        }
    }
}
console.log("unsolved ones are " + unsolved)
visualizeSolved(); 

console.log(rows); 
