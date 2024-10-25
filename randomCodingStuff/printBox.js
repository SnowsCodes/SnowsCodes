function isntObject (name, input) {
    
    var rows = ["", "", ""];
    var empty = "| "; 
    
    for (var i = 0; i < name.length; i++) {
        if (i == 0) {
            rows[0] += "┌" + name[0]; 
            rows[1] += "│ " + input[name[0]] + " "; 
            rows[rows.length-1] += "└"; 
        }
        
        
        var type = "" + typeof input[name[i]]; 
        var maxLen = Math.max(Math.max(rows[0].length, rows[1].length), type.length + rows[rows.length-1].length);
        
        while (rows[0].length < maxLen) {
            rows[0] += "─";
        }
        
        for (var j = 1; j < rows.length-1; j++) {
            while (rows[j].length < maxLen) {
                rows[j] += " ";
            }
        }
        
        while (empty.length<maxLen) {
            empty += " "; 
        }
        
        while (rows[rows.length-1].length + type.length < maxLen) {
            rows[rows.length-1] += "─";
        }
        rows[rows.length-1] += type; 
        
        if (i < name.length-1) {
            rows[0] += "┬" + name[i+1];
            //rows[1] += "│ ";
            for (var j = 1; j < rows.length-1; j++) {
                rows[j] += "│ ";
            }
            empty += "│ ";
            rows[rows.length-1] += "┴";
            if (typeof input[name[i+1]] != typeof {}) {
                rows[1] += input[name[i+1]] + " ";
            } else {
                var len = rows.length; 
                for (var j = rows.length; j < input[name[i+1]].length; j++) {
                    rows.push(empty); 
                }
                rows.push(rows[len-1]); 
                rows[len-1] = empty; 
                for (var j = 0; j < input[name[i+1]].length; j++) {
                  rows[j+1] += input[name[i+1]][j];
                }
            }
        } else {
            rows[0] += "┐ ";
            for (var j = 1; j < rows.length-1; j++) {
                rows[j] += "│ "; 
            }
            rows[rows.length-1] += "┘ "; 
        }
    }
    
    var returnVal = ""; 
    for (var i = 0; i < rows.length; i++) {
        returnVal += rows[i] + "\n"
    }
    return returnVal; 
    
    //return (rows[0] + "\n" + rows[1] + "\n" + rows[rows.length-1]);
    //return [rows[0], rows[1], rows[rows.length-1]];
}

function printBox (name, input) {
    if (typeof input == typeof {}) {
        var index = Object.getOwnPropertyNames(input); 
        var len = index.length;
        var objects = {}; 
        var indexOfObj = []; 
        for (var i = index.length-1; i >= 0; i--) {
            if (typeof input[index[i]] == typeof {}) {
                objects[i] = printBox(index[i], input[index[i]]).split("\n");
                input[index[i]] = objects[i];
                index.length -= 1;    
            }
        }
        
        
        if (index.length == len) {
            var out = isntObject(index, input);
        } else {
            var temp = isntObject(index, input).split("\n");
            for (var i = 0; i < temp.length; i++) {
                temp[i] = "│ " + temp[i] + "│";
            }
            var out = "┌" + name;
            for (var i = out.length; i < temp[0].length-1; i++) {
                out += "─";
            }
            out += "┐ "; 
            for (var i = 0; i < temp.length-1; i++) {
                out += "\n" + temp[i]; 
            }
            out += "\n└";
            for (var i = 1; i < temp[0].length-7; i++) {
                out += "─";
            }
            out += "object┘ "
        }
    } else {
        var out = isntObject([name], {[name]: input}); 
    }
    return out; 
}

var inputArray = {"a": 1, "b": "2", "c": "423879"};
console.log("out is: \n" + printBox("aa", inputArray));
inputArray = 1;
console.log("out is: \n" + printBox("aa", inputArray));
inputArray = [1, 2, 3, [1, 2]];
console.log("out is: \n" + printBox("aa", inputArray));
inputArray = [1, [1, 2], 2, 3];
console.log("out is: \n" + printBox("aa", inputArray));
inputArray = [1, [1, 2], [1, 2], 2, 3];
console.log("out is: \n" + printBox("aa", inputArray));
inputArray = [1, [1, 2], [1, ["a", "b"]], 2, 3];
console.log("out is: \n" + printBox("aa", inputArray));




