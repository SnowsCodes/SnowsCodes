var letters = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];

function sumArray (array) {
  var sum = 0; 
  for (var i = 0; i < array.length; i++) {
    sum = sum + array[i]; 
  }
  return sum; 
}

function checkArray (array1, array2) {
    if (array1.length != array2.length) {
        return false; 
    }
    
    for (var i = 0; i < array1.length; i++) {
        if (array1[i] != array2[i]) {
            return false
        }
    }
    
    return true
}

function createCombos (arrayInput, pLength, arrayOut) {
 
    //create array to solve number of spaces between filled boxes
    //there are arrayInput.length plus one spaces in total, but the last one can be calculated later, so there will be arrayInput.length numbers in the array
    var numSpacesBetween = []; 
    for (var i = 0; i < arrayInput.length; i++) {
        numSpacesBetween.push(0); 
    }
    
    //calculate number of spaces left other than filled boxes and mandatory 1 space between each box
    //there are a total of pLength number of boxes
    //minusing the sum of the arrayInput gives the number of total spaces in the line
    //then minus (the length of the array minus 1) because those are the mandatory spaces between filled boxes
    var spacesLeft = pLength - (sumArray(arrayInput) + arrayInput.length - 1)
    
    //create an output array
    var tempOut = []; 
    
    //create comparedArray, which is the array of the last value in the while loop
    var comparedArray = [];
    for (var i = 0; i < arrayInput.length - 1; i++) {
        comparedArray.push(0); 
    }
    comparedArray.push(spacesLeft);
    
    //check if comparedArray and numSpacesBetween are the same
    var same = checkArray(comparedArray, numSpacesBetween);
    
    
    
    //main loop to solve for all possibilities
    //counter is there to limit the number of times it loops, can delete after finished
    var counter = 0; 
    while (!same && counter < 100) {
        
        //add one to the first number in numSpacesBetween
        //if the first number exceeds spacesLeft, it is impossible for it to be a possibility of the pattern, so it'll carry over
        //it will continue checking if there is a need to carry over until either it gets to the last number in numSpacesBetween or until the variable doesn't need to carry over
        numSpacesBetween[0] += 1;
        for (var i = 0; i < numSpacesBetween.length; i++) {
            if (spacesLeft < sumArray(numSpacesBetween)) {
                numSpacesBetween[i] = 0; 
                numSpacesBetween[i+1]++;
            } else {
                break;
            }
        }
        
        //there is a new variable used to calculate the number of boxes in the last spaces, every loop it will minus numSpacesBetween[i]
        //if the sum of the numbers in numSpacesBetween is less than or equal to spacesLeft, it will add one to all the number except the first one, then change all the numbers into a string and then put them into a temporary array
        //after putting them into a temporary array, a temporary string is created
        //the string takes the first value in tempArray, add the first value in tempArray, add the second value in arrayInput and so on until it adds the last value in tempArray
        var lastNum = spacesLeft; 
        var tempArray = []; 
        if (sumArray(numSpacesBetween) <= spacesLeft) {
            lastNum -= numSpacesBetween[0];
            var tempNum = letters[numSpacesBetween[0]];
            tempArray.push(tempNum); 
            for (var i = 1; i < numSpacesBetween.length; i++) {
                lastNum -= numSpacesBetween[i];
                var tempNum = letters[numSpacesBetween[i] + 1];
                tempArray.push(tempNum); 
            }
            var tempString = ""; 
            for (var i = 0; i < tempArray.length; i++) {
                tempString += tempArray[i]; 
                tempString += arrayInput[i]; 
            }
            tempString += lastNum; 
            arrayOut.push(tempString);
        }
        console.log(numSpacesBetween); 
        
        counter++;
        var same = checkArray(comparedArray, numSpacesBetween);
    }
}

var inputArray = [1, 3];
var outputArray = []; 
createCombos (inputArray, 10, outputArray);
console.log(outputArray); 
