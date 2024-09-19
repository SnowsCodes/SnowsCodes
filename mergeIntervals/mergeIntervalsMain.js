//the intervals you want to merge :)
var intervals = [[1, 2], [3, 4], [1, 4]]; 
var length = intervals.length; 

function merge (a1, a2) {
    if (a1[0] <= a2[0] && a1[1] >= a2[0]) {
        if (a1[0] <= a2[1] && a1[1] >= a2[1]) {
            return a1; 
        } else {
            return [a1[0], a2[1]];
        }
    } else if (a1[0] <= a2[1] && a1[1] >= a2[1]) {
        return [a2[0], a1[1]]; 
    } else {
        return [a1, a2, 0];
    }
}

function repeat (array) {
    var num = array.length;
    var rep = 1; 
    for (var i = 1; i < num; i++) {
        rep = rep * i; 
    }
    var rep = rep/2; 
    var a = 0; 
    var b = 1; 
    var index = [[a, b]]; 
    for (var i = 0; i < rep + 1; i++) {
        if (b != array.length-1) {
            b++; 
        } else {
            a++; 
            b = a+1; 
        }
        index.push([a, b]); 
    }
    
    for (var i = 0; i < index.length; i++) {
        console.log(i); 
        var temp = merge(intervals[index[i][0]], intervals[index[i][1]]); 
        console.log (intervals[index[i][0]] + "   " + intervals[index[i][1]]);
        console.log("temp length is: ");
        console.log(temp.length); 
        if (temp.length == 2) {
            console.log("ran this"); 
            array.splice(index[i][1], 1); 
            array.splice(index[i][0], 1); 
            array.push(temp); 
            return [array, true]; 
        }
    }

    return [array, false]; 
    
}

var result; 

if (length == 2) {
    result = merge(intervals[0], intervals[1]); 
} else if (length > 2) {
    var tempResult = repeat(intervals); 
    console.log(tempResult); 
    while (tempResult[1] && tempResult[0].length > 1) {
        var tempResult = repeat(intervals); 
        console.log(tempResult);
    }
    result = tempResult[0]; 
} else {
    result = intervals; 
}

console.log(result[0]); 
