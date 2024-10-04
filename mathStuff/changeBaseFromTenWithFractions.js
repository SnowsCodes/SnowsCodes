function changeBase (baseTen, newBase, maxDigit) {
    //separate baseTen into the whole number part and the decimal part
    //the + converts the string into a number 
    var index = baseTen.indexOf("."); 
    if (index > -1) {
        var whole = +baseTen.slice(0, index); 
        //store the decimal part with num as the numerator and denom as the denominator
        var num = +baseTen.slice(index+1);
        var denom = 10 ** baseTen.slice(index+1).length; 
    } else {
        var whole = +baseTen; 
        var num = 0; 
        var denom = 1; 
    }
    //console.log(whole + "  " + num + "/" + denom); 
    
    
    //highestPower calculates the highest power of the newBase that will be used in calculating 
    var highestPower = Math.floor(Math.log(whole) / Math.log(newBase)); 
    var returnVal = ""; 
    //create array that changes the number into a digit 
    //if the newBase is greater than 35, add the digit in parethesis like (40)
    var remap = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
    for (var i = remap.length-1; i < newBase; i++) {
        remap.push("(" + (i + 1) + ")");
    }
    
    
    //calculates number until it gets to the decimal
    for (var i = highestPower; i >= 0; i--) {
        var nextDigit = Math.floor(whole / (newBase ** i));
        whole -= nextDigit * (newBase ** i);
        returnVal += remap[nextDigit]; 
    }
    
    //if the denominator is 1, that means it is a whole number and the value is returned
    //otherwise, continue on and first add a decimal place
    if (denom == 1) {
        return returnVal; 
    }
    returnVal += "."; 
    
    //console.log(num + "/" + denom); 
    
    //if there isn't a value entered for maxDigit, set maxDigit equal 10
    if (!(typeof maxDigit == typeof 1)) {
      maxDigit = 10; 
    }
    
    var counter = 0; 
    var numDiv = denom; 
    while (num != 0 && counter < maxDigit) {
        num *= newBase; 
        denom *= newBase; 
        nextDigit = Math.floor(num/numDiv);
        num -= nextDigit * numDiv;
        returnVal += remap[nextDigit]; 
        
        counter++; 
    }
    
    //console.log(denom);
    return returnVal; 
}

console.log(changeBase ("36", 37));
