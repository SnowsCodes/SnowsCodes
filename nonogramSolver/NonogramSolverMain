//this program takes inputs for a nonogram and the dimensions of the nonograms
//then, it’ll print the output in the console
//zeroes means that the box is not filled, and ones means that the box is filled
//this program has a higher chance of encountering an error for more boxes in each row and column

//set up dimensions
//creates a prompt to ask how many rows and columns the nonogram has
var rows = prompt("How many rows does the nonogram have? ");
var columns = prompt("How many columns does the nonogram have? ")

//converts the input into a number
rows = +rows; 
columns = +columns

//set up horizontal inputs
var hInputs = []; 
for (i = 0; i < rows; i++) {
    hInputs.push(prompt("What is the inputs in row number " + (i+1) + "? "));
}

//set up vertical inputs
var vInputs = [];
for (i = 0; i < columns; i++) {
    vInputs.push(prompt("What is the inputs in column number " + (i+1) + "? "));
}



//0 means unfilled, 1 means filled, 2 means undetermined

//set up letters for numbers with two or more digits (ex: 10 would be A, 11 would be B, all the way to 35 is Z) 
var letters = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];

//if the number of inputs does not match the number of columns or rows for vertical inputs and horizontal inputs respectively
if(vInputs.length != columns || hInputs.length != rows) {
    console.log("Error, input is not the right length")
}

//set up the solved arrays
{
    // set up horizontal arrays
    var horizontal =[];
    var hCombos = []; 
    for (var i = 0; i < rows; i++) {
        var temp = []; 
        for (var j = 0; j < columns; j++) {
            temp.push(2); 
        }
        horizontal.push(temp); 
        hCombos.push([]); 
    }
    
    var vertical =[];
    var vCombos = []; 
    for (var i = 0; i < columns; i++) {
        var temp = []; 
        for (var j = 0; j < rows; j++) {
            temp.push(2); 
        }
        vertical.push(temp); 
        vCombos.push([]); 
    }
}

//change the information in the vertical arrays to match it with the horizontal arrays
function syncV () {
    for (var i = 0; i < columns; i++) {
        for (var j =  0; j < rows; j++) {
            vertical[i][j]  = horizontal[j][i]; 
        }
    }
} 

//change the information in the horizontal arrays to match it with the vertical arrays
function syncH () {
    for (var i = 0; i < rows; i++) {
        for (var j =  0; j < columns; j++) {
            horizontal[j][i] = vertical[i][j];  
        }
    }
} 

//takes two inputs and use it to calculate n Choose k, and returns a number
function nChooseK (n, k) {
    function factorial (input) {
        var out = 1; 
            for (i = 1; i < input + 1; i++) {
                out = out * i; 
            }
        return out; 
    }
    var num = factorial(n); // numerator
    var denom1 = factorial(k); //denominator 1
    var denom2 = factorial(n-k); //denominator 2
    var denom = denom1 * denom2 //total denominator
    var total = num/denom; 
    return total; 

}

//takes an array and returns the sum of all the elements of the array
function sumArray (array) {
  var sum = 0; 
  for (var i = 0; i < array.length; i++) {
    sum = sum + array[i]; 
  }
  return sum; 
}

//create all possible combinations for a row or column for a set length of the pattern, and stores the combinations in an inputted array
function createCombos (arrayInput, pLength, arrayOut) {
    
    //create temporary array
    var temp = []; 
    for (var i = 0; i < arrayInput.length; i ++) {
        temp.push (0); 
    }
    
    //find the number of spaces other than filled spaces and the number of spaces in between the filled spaces
    var left = pLength - (sumArray(arrayInput) + arrayInput.length - 1);
    
    //create array of maximum values
    var max = []; 
    for (var i = 0; i < arrayInput.length; i++) {
        max [i] = left; 
    }
    
    //calculate the number of repetitions using the stars and bars method by using the nChooseK function from before
    var nValue = left;
    var kValue = arrayInput.length + 1;
    var rep = nChooseK((nValue + kValue - 1), (kValue - 1)); 
    
    
    if (left < 0) {
        //if there is a negative number of spaces left, return with an error
        console.log("Error");
    } else {
        //if not, create a loop to create all the possible combinations of spaces, excluding the known one (for example, if the input is a 2 and a 3, there is definitely a space between them, which would be excluded in this loop)
        for (var i = 0; i < rep; i++) {
            for (var j = 0; j < temp.length; j++) {
                if (temp[j] > max[j]) {
                    if (j==0) {
                        //the number of spaces on the first interval would increase by one each time, but if it goes past the maximum, it’ll go to zero and increase the next number
                        temp[0] = 0; 
                        temp[1] = temp[1] + 1;
                        max[0] = max[0] - 1; 
                        if (max[0] < 0) {
                            
                        }
                    } else {
                        //if the any other number other than the first one hits the max set for it, it’ll increase the next number and reset the maximum for the previous numbers
                        temp[j] = 0; 
                        if (j<temp.length - 1) {
                            temp[j+1] = temp[j+1] + 1;
                        }
                        max[j] = max[j] - 1;
                        for (var k = 0; k < j; k++) {
                            max[k] = pLength - (sumArray(arrayInput) + sumArray(temp) + arrayInput.length - 1)
                        }
                    }
                }
            }
            
            //create an output for one loop
            var output = "";

            //convert all the numbers in the input array into letters if it is more than 9, then store it in tempArray (not temp)
            var tempArray = []; 
            for (var j = 0; j < arrayInput.length; j++) {
                var tempNum = arrayInput[j]; 
                tempNum = letters[tempNum]
                tempArray.push(tempNum); 
            }

            //change the first number in temp into a number, then convert it into a letter if it is more than 9
            //then, store the number or  letter into the output string
            var first = +temp[0]; 
            var first = letters[first]; 
            output = output + first;

            //store the first number or letter in the input array into the output string
            output = output + tempArray[0];  

            //create a loop, where for each number in temp other than the first one would increase by one, change into a number, then convert into a letter if its more than 9, and then after that add the next number or letter from the input array
            //this means that the string would indicate the number of spaces in the first interval, then number of filled, and alternate between them
            //for example, 3421 would indicate 3 spaces, then 4 filled, then 2 spaces, and then 1 filled
            for (var j = 1; j < temp.length; j++) {
                var tempNum = temp[j] + 1; 
                tempNum = +tempNum; 
                tempNum = letters[tempNum]; 
                output = output + tempNum; 
                output = output + tempArray[j]; 
            }

            //add on the number of spaces left
            var last = left - sumArray(temp);
            last = letters[last]; 
            output = output + last; 
            
            //add the output string to the end of the output array 
            arrayOut.push(output);
            
            //Increase the first number of temp
            temp[0] = temp[0] + 1; 
         }
    }
}

//takes input from pattern (a string of 0's and 1's, can take numbers or string), returns a string
function compare (array) {
    
    if (array.length == 1) {
        return array; 
    }
    
    //set the first array as a reference, and split it up into digits
    var compared = (""+array[0]).split("");
    
    //for each array other than the first one, compare it with the first array
    //if a digit matches, nothing changes
    //if a digit doesn't match, change the digit in the reference array as 2 to indicate that it isn't always one number
    for (var i  = 1; i < array.length; i++) {
        var array2 = (""+array[i]).split(""); 
        for (var j = 0; j < compared.length; j++) {
            if (compared[j] !== array2[j]) {
                compared[j] = "2";
            }
        }
    }
    
    //create result string, which concatenates all the elements in the array
    var result = ""; 
    for (var i = 0; i < compared.length; i++) {
        result = result + compared[i]; 
    }
    
    //return the compared array
    return result; 
}

//create patterns in 0's and 1's using input from createCombos (for example, 131 would mean 1 empty, 3 filled, then 1 empty. This array will return it as 01110 since 0 means empty and 1 means filled)
function createPattern (input) {

    // create result variable
    var result = ""; 

    //split the input and put it in an array
    var temp = (""+input).split(""); 
    var array = [];
    for (var i = 0; i < temp.length; i++) {
        array.push(letters.indexOf(temp[i]));
    }

    //change each string in the array into a number
    for (var i = 0; i < array.length; i++) {
        array[i] = +array[i]; 
    }

    // use the array to change the input into a result (in 0s and 1s)
    for (var i = 0; i < array.length; i++) {
        for (var j = 0; j < array[i]; j++) {
            var result = result + ((Math.round(i/2) - i/2)*2).toString(); 
        }
    }
    
    //return the result (which is a string)
    return result; 
}

//compare each element in the array with a reference 
//the reference would be the solved parts of the nonogram
//if the compared array has a different number from the reference, delete that array from the original array
//returns an array without the possibilities that don’t match with the already solved boxes
function compareUpdate (array, comparison) { 
    //create another array the same as the inputted comparison array
    var compared = comparison;
    var toss = [];
    for (var i = 0; i < array.length; i++) {
        toss.push(0); 
    }
    
    for (var i = 0; i < array.length; i++) {
        var temp = (""+array[i]).split("");
        for (var k = 0; k < temp.length; k++) {
            if (temp[k] != compared[k] && compared[k] != 2) {
                toss[i] = 1; 
            }
        }
    }
    
    var temp1 = []; 
    for (var i = 0; i < toss.length; i++) {
        if (toss[i] == 0) {
            temp1.push(array[i]); 
        }
    }
    
    return temp1; 
}

//generate all possibilities for patterns in each row using the input and store in hCombos
for (var j = 0; j < hInputs.length; j++) {
    var comboInput = []; 
    var tempInput = (""+hInputs[j]).split("");
    for (var i = 0; i < tempInput.length; i++) {
        comboInput.push(letters.indexOf(tempInput[i]));
    }
    
    for (var i = 0; i < comboInput.length; i++) {
            comboInput[i] = +comboInput[i]; 
    }
    createCombos (comboInput, columns, hCombos[j]); 
    for (var i = 0; i < hCombos[j].length; i++) {
        hCombos[j][i] = createPattern(hCombos[j][i])
    }
}


//compare all the possibilities for each row and store the overlaps in the array horizontal
function updateH () {
    for (var j = 0; j < hCombos.length; j++) {
        var tempStr = compare(hCombos[j]); 
        var tempArray = (""+tempStr).split("");
        horizontal[j] = tempArray; 
    }
}

//compare all the possibilities for each column and store the overlaps in the array vertical
function updateV () {
    for (var j = 0; j < vCombos.length; j++) {
        var tempStr = compare(vCombos[j]); 
        var tempArray = (""+tempStr).split("");
        vertical[j] = tempArray; 
    }
}

//generate all possibilities for patterns in each column using the input and store in hCombos
for (var j = 0; j < vInputs.length; j++) {
    var comboInput = []; 
    var tempInput = (""+vInputs[j]).split("");
    for (var i = 0; i < tempInput.length; i++) {
        comboInput.push(letters.indexOf(tempInput[i]));
    }
    
    for (var i = 0; i < comboInput.length; i++) {
            comboInput[i] = +comboInput[i]; 
    }
    createCombos (comboInput, rows, vCombos[j]); 
    for (var i = 0; i < vCombos[j].length; i++) {
        vCombos[j][i] = createPattern(vCombos[j][i])
    }
}

//create a reference array with all the solved boxes
var ref = []; 
for (var i = 0; i < horizontal.length; i++) {
    ref.push(horizontal[i]); 
}

//create a counter
var counter = 0; 

//while the counter is less than zero, run the while loop
while (counter < 1) {

    //compare all the possibilities for each row and store the overlap in the solved arrays
    updateH();

    //sync the solved horizontal array and the solved vertical arrays
    syncV();

    //for each possible combination, remove all the ones that contradict with the solved boxes
    for (var j = 0; j < vCombos.length; j++) {
        vCombos[j] = (compareUpdate (vCombos[j], vertical[j])); 
    }

    //compare all the possibilities for each column and store the overlap in the solved arrays
    updateV(); 

    //sync the solved vertical arrays and the solved horizontal arrays
    syncH();  

    //for each possible combination, remove all the ones that contradict with the solved boxes
    for (var j = 0; j < hCombos.length; j++) {
        hCombos[j] = (compareUpdate (hCombos[j], horizontal[j])); 
    }
    
    //creates a two counters
    //always increase the first counter, and increase the second counter if the element in the solved arrays is the same as the one in the reference array
    var counter1 = 0; 
    var counter2 = 0; 
    for (var j = 0; j < horizontal.length; j++) {
        for (var k = 0; k < horizontal[j].length; k++) {
            counter1++; 
            if (ref[j][k] === horizontal[j][k]) {
                counter2++;
            }
        }
    }
    
    //if the two counters are equal, this means nothing changed in this loop, so counter will increase to stop the loop
    // if the two counters are not equal, then update the reference loop to prepare for the next loop
    if(counter1 == counter2) {
        counter++ 
    } else {
        var ref = []; 
        for (var i = 0; i < horizontal.length; i++) {
            ref.push(horizontal[i]); 
        }
    }
    
}

for(var aa = 0; aa < horizontal.length; aa++) {
    console.log("Horizontal is " + horizontal[aa]); 
}
console.log(""); 


