function changeBase (baseTen, newBase) {
  var index = baseTen.indexOf("."); 
  console.log(index); /*
  //default denominator = 1
  var denom = 1; 
  //highestPower calculates the highest power of the newBase that will be used in calculating 
  var highestPower = Math.floor(Math.log(baseTen) / Math.log(newBase)); 
  var returnVal = ""; 
  //create array that changes the number into a digit 
  //if the newBase is greater than 35, add the digit in parethesis like (40)
  var remap = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
  for (var i = remap.length-1; i < newBase; i++) {
    remap.push("(" + i + ")");
  }
  
  //if baseTen is not an integer convert the number into a fraction 
  //with the numerator stored in baseTen and the denominator in denom
  while (Math.floor(baseTen) != baseTen) {
    baseTen *= 10; 
    denom *= 10; 
  }
  //simplify the fraction
  while (denom%2 == 0 && baseTen%2 == 0) {
    denom /= 2; 
    baseTen /= 2; 
  }
  while (denom%5 == 0 && baseTen%5 == 0) {
    denom /= 5; 
    baseTen /=5; 
  }
  console.log(baseTen + "/" + denom); 
  
  //calculates number until it gets to the decimal
  for (var i = highestPower; i >= 0; i--) {
    var nextDigit = Math.floor(baseTen / (denom * (newBase ** i)));
    returnVal += nextDigit; 
    baseTen -= nextDigit * denom * (newBase ** i);
  }
  
  //if the denominator is 1, that means it is a whole number and the value is returned
  //otherwise, continue on and first add a decimal place
  if (denom == 1) {
    return returnVal; 
  }
  returnVal += "."; 
  
  console.log(baseTen + "/" + denom); 
  
  return returnVal; */
}

console.log(changeBase ("467.372819", 4)); 



