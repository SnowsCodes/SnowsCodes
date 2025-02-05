function fromNum (num) {
    if (num >= 4000 || num <= 0) {
        return "ERROR"; 
    }
    
    var out = "";  
    var full = ["I", "X", "C", "M"];
    var half = ["V", "L", "D"];
    
    for (var i = 3; i >= 0; i--) {
        var digit = Math.floor(num/(10**i)); 
        
        if (digit == 9) {
            out += full[i] + full[i+1];
        } else if (digit == 4) {
            out += full[i] + half[i];
        } else {
            if (digit >= 5) {
                out += half[i]; 
                digit -= 5; 
            }
            out += full[i].repeat(digit); 
        }
        
        num %= 10**i;
    }
    
    return out; 
}



function toNum (str) {
    if (str == "") {
        return -1; 
    }
    
    var out = 0;  
    var id = str.length-1; 
    
    var full = ["I", "X", "C", "M"];
    var half = ["V", "L", "D"];
    
    for (var i = 0; i < 4 && id >= 0; i++) {
        if (id > 0 && str.charAt(id-1) == full[i] && str.charAt(id) == half[i]) {
            out += 4 * (10**i); 
            id -= 2; 
        } else if (id > 0 && str.charAt(id-1) == full[i] && str.charAt(id) == full[i+1]) {
            out += 9 * (10**i); 
            id -= 2; 
        } else {
            for (var j = 0; j < 3 && id >= 0 && str.charAt(id) == full[i]; j++) {
                out += 1 * (10**i); 
                id--; 
            }
            
            if (id >= 0 && str.charAt(id) == half[i]) {
                out += 5 * (10**i);
                id--; 
            }
        }
    }
    
    if (id != -1) {
        return -1; 
    } 
    return out; 
}


var a = [1, 2, 3, 4, 5, 6, 7]; 

for (var i = 0; i < a.length; i++) {
    console.log("The inputed string is: " + a[i] + "\nThe ouputted number is: " + toNum(a[i]) + "\n");
}

for (var i = 0; i < 100; i++) {
    console.log("The inputed string is: " + fromNum(i) + "\nThe ouputted number is: " + toNum(fromNum(i)) + "\n");
}



