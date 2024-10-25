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
    
    while (rows[1].length < maxLen) {
      rows[1] += " ";
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
      /*for (var i = 2; i < rows.length-1; i++) {
        rows[i] += "│ ";
      }*/
      rows[1] += "│ ";
      empty += "│ ";
      rows[rows.length-1] += "┴";
      if (typeof input[name[i+1]] != typeof {}) {
        rows[1] += input[name[i+1]] + " ";
      } else {
        rows.push(empty);
        rows.push(rows[2]); 
        rows[2] = empty; 
        rows[1] += input[name[i+1]][0] + " ";
        rows[2] += input[name[i+1]][1] + " ";
        rows[3] += input[name[i+1]][2] + " ";
      }
    } else {
      rows[0] += "┐";
      for (var j = 1; j < rows.length-1; j++) {
          rows[j] += "│"; 
      }
      rows[rows.length-1] += "┘"; 
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
        objects[i] = printBox(index[i], input[index[i]]);
        input[index[i]] = objects[i];
        //delete input[index[i]];
        //delete index[i];
        index.length -= 1;  
      }
    }
    
    //console.log("input: \n" + input[index[3]]);
    
    if (index.length == len) {
      
      var out = isntObject(index, input);
    } else {
      //console.log(objects); 
      var processed = objects['3'].split("\n");
      input[3] = processed; 
      console.log(typeof input[3]);
      console.log("index: " + index + "\ninput: " + input)
      var out = isntObject(index, input);
    }
  } else {
    var out = isntObject([name], {[name]: input}); 
  }
  return out; 
}

var inputArray = {"a": 1, "b": "2", "c": "423879"};
//console.log("out is: \n" + printBox("aa", inputArray));
inputArray = [1, 2, 3, [1, 2]];
//console.log(isntObject(["1", "2", "3"], [1, "2", 3]));
console.log("out is: \n" + printBox("aa", inputArray));
//printBox("a", 1321321321)





