var remap = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
function changeBase (input, base, max) {
    var rep = Math.floor(Math.log(input)/Math.log(base) + 1); 
    var result = ""; 
    var remainder = input; 
    for (var i=rep-1; i > -(max+1); i--) {
        result += remap[Math.floor(remainder/(base ** (i)))]?? "?"; 
        remainder = Math.round((remainder % (base ** (i)))*(10**(max+1)))/(10**(max+1));
        if (remainder == 0 && i == 0) {
            return result
        } else if (i == 0) {
            result += ".";
        }
    }
    return result; 
}

var baseTen = 9561; 
var maxDigits = 215; 
var newBase = 30; 
console.log(changeBase (baseTen, newBase, maxDigits)) ;
