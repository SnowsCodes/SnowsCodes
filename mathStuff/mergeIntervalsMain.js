var intervals = [[5, 5], [5, 8]]; 
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
    } else if (a2[0] <= a1[0] && a2[1] >= a1[0]) {
        if (a2[0] <= a1[1] && a2[1] >= a1[1]) {
            return a2; 
        } else {
            return [a2[0], a1[1]];
        }
    } else if (a2[0] <= a1[1] && a2[1] >= a1[1]) {
        return [a1[0], a2[1]]; 
    } else {
        return [a1, a2, 0];
    }
}

function repeat (array) {
    var num = array.length;
    var rep = (num*(num-1))/2; 
    var a = 0; 
    var b = 1; 
    var index = [[a, b]]; 
    for (var i = 0; i < rep -1; i++) {
        if (b != array.length-1) {
            b++; 
        } else {
            a++; 
            b = a+1; 
        }
        index.push([a, b]); 
    }
    
    for (var i = 0; i < index.length; i++) {
        var temp = merge(intervals[index[i][0]], intervals[index[i][1]]); 
        if (temp.length == 2) {
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
    if (result[result.length-1] == 0) {
        result.splice (result.length-1, 1); 
    }
} else if (length > 2) {
    var tempResult = repeat(intervals); 
    while (tempResult[1] && tempResult[0].length > 1) {
        var tempResult = repeat(intervals); 
    }
    result = tempResult[0]; 
} else {
    result = intervals; 
}

if (result.length == 1) {
    console.log(result[0]);
} else {
    console.log(result); 
}
