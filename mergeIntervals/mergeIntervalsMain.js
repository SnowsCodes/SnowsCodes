//the intervals you want to merge :)
var intervals = [[1, 2], [3, 4], [2, 4]]; 
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

if (length == 2) {
    var result = merge(intervals[0], intervals[1]); 
} else if (length > 2) {
    for (var i = 0; i < length!; i++) {
        
    }
} else {
    result = intervals; 
}

console.log(result); 
