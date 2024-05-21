var remap = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
function changeBase (input, base, max) {
    for (var i=0; i < base + 1 - remap.length; i++) {
        remap.push("?"); 
    }
    var rep = Math.floor(Math.log(input)/Math.log(base) + 1); 
    var result = ""; 
    var remainder = input; 
    for (var i=rep-1; i > -max; i--) {
        var num = base ** (i); 
        result = result + remap[Math.floor(remainder/num)]; 
        remainder = Math.round((remainder % num)*(10**max))/(10**max);
        if (remainder == 0 && i == 0) {
            return result
        } else if (i == 0) {
            result += ".";
        }
    }
    return result; 
}

var baseTen = 4.489453489765; 
var maxDigits = 215 +1; 
var newBase = 5; 
console.log(changeBase (baseTen, newBase, maxDigits)) ;
