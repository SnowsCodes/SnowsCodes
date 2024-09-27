var n = 25; 
var d = 60; 

function simplifyFraction (num, denom) {
  var prime = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47]; 
  var numFactors = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]; 
  var denomFactors = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];  

  while (num > 1) {
    for (var i = 0; i < prime.length-1; i++) {
      if (num % (prime[i]) == 0) {
        numFactors[i]++; 
        num /= prime[i]; 
      }
    }
  }
  
  while (denom > 1) {
    for (var i = 0; i < prime.length-1; i++) {
      if (denom % (prime[i]) == 0) {
        denomFactors[i]++; 
        denom /= prime[i]; 
      }
    }
  }
  
  for (var i = 0; i < prime.length-1; i++) {
    if (denomFactors[i] >= 1 && numFactors[i] >= 1) {
      if (denomFactors[i] > numFactors[i]) {
        denomFactors[i] -= numFactors[i]; 
        numFactors[i] = 0; 
      } else {
        numFactors[i] -= denomFactors[i]; 
        denomFactors[i] = 0; 
      }
    }
  }
  
  var outNum = 1; 
  for (var i = 0; i < prime.length-1; i++) {
    outNum *= prime[i] ** numFactors[i]; 
  }
  
  var outDenom = 1; 
  for (var i = 0; i < prime.length-1; i++) {
    outDenom *= prime[i] ** denomFactors[i]; 
  }
  
  return [outNum, outDenom]; 
}

console.log(simplifyFraction (n, d));
