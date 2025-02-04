class Main {
    //inputs
    
    static int pos = -1; 

    /*static int[][] unsolved = { 
        {6, 0, 0, 0, 8, 0, 0, 5, 0}, 
        {0, 0, 9, 0, 0, 6, 0, 1, 0}, 
        {0, 0, 0, 9, 0, 2, 0, 0, 0}, 
        {9, 0, 0, 0, 0, 0, 0, 0, 2}, 
        {3, 0, 0, 0, 1, 0, 0, 0, 7}, 
        {0, 0, 8, 7, 2, 0, 0, 0, 4}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {8, 0, 0, 0, 7, 0, 0, 3, 0}, 
        {0, 4, 0, 0, 6, 0, 0, 0, 1}, 
    };*/ 
    
    static int[][] unsolved = { 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    }; 
    
    static int[][] s = { 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    }; 
    
    //for use in check
    static int[][] box = {
        { 0,  1,  2,  9, 10, 11, 18, 19, 20}, 
        { 3,  4,  5, 12, 13, 14, 21, 22, 23}, 
        { 6,  7,  8, 15, 16, 17, 24, 25, 26}, 
        {27, 28, 29, 36, 37, 38, 45, 46, 47}, 
        {30, 31, 32, 39, 40, 41, 48, 49, 50}, 
        {33, 34, 35, 42, 43, 44, 51, 52, 53}, 
        {54, 55, 56, 63, 64, 65, 72, 73, 74}, 
        {57, 58, 59, 66, 67, 68, 75, 76, 77}, 
        {60, 61, 62, 69, 70, 71, 78, 79, 80}, 
    };
    
    //converts from index (goes from 0 to 80) to the x and y coords
    //row num is first number, col num is second number
    public static int[] convert (int in) {
        return new int[] {in/9, in%9}; 
    }
    
    //stands for convert row
    public static int cRow (int in) {
        return in/9; 
    }
    
    //stands for convert column
    public static int cCol (int in) {
        return in%9; 
    }
    
    public static boolean checkCage (int id) {
        //check in cages
        int sum = -1; 
        if (id == 1) {
            sum = s[0][0] + s[0][1]; 
        } else if (id == 3) {
            sum = s[0][2] + s[0][3]; 
        } else if (id == 13) {
            sum = s[0][4] + s[1][3] + s[1][4]; 
        } else if (id == 14) {
            sum = s[0][5] + s[0][6] + s[1][5]; 
        } else if (id == 23) {
            sum = s[2][3] + s[2][4] + s[2][5]; 
        } else if (id == 24) {
            sum = s[0][7] + s[1][6] + s[1][7] + s[2][6]; 
        } else if (id == 26) {
            sum = s[0][8] + s[1][8] + s[2][8]; 
        } else if (id == 27) {
            sum = s[1][0] + s[2][0] + s[3][0]; 
        } else if (id == 28) {
            if (s[3][1] != s[1][2] && s[3][1] != s[2][2]) {
                sum = s[1][1] + s[1][2] + s[2][1] + s[2][2] + s[3][1]; 
            } else {
                return false; 
            }
        } else if (id == 34) {
            if (s[2][7] != s[3][6]) {
                sum = s[2][7] + s[3][6] + s[3][7]; 
            } else {
                return false; 
            }
        } else if (id == 44) {
            sum = s[3][8] + s[4][7] + s[4][8]; 
        } else if (id == 45) {
            sum = s[4][0] + s[4][1] + s[4][2] + s[5][0]; 
        } else if (id == 52) {
            sum = s[4][6] + s[5][6] + s[5][7]; 
        } else if (id == 56) {
            sum = s[6][1] + s[6][2]; 
        } else if (id == 61) {
            sum = s[6][6] + s[6][7]; 
        } else if (id == 62) {
            sum = s[5][8] + s[6][8]; 
        } else if (id == 63) {
            sum = s[6][0] + s[7][0]; 
        } else if (id == 65) {
            sum = s[7][1] + s[7][2]; 
        } else if (id == 67) {
            sum = s[6][3] + s[6][4] + s[7][3] + s[7][4]; 
        } else if (id == 71) {
            sum = s[7][6] + s[7][7] + s[7][8]; 
        } else if (id == 74) {
            sum = s[8][0] + s[8][1] + s[8][2]; 
        } else if (id == 76) {
            sum = s[8][3] + s[8][4]; 
        } else if (id == 77) {
            sum = s[6][5] + s[7][5] + s[8][5]; 
        } else if (id == 80) {
            sum = s[8][6] + s[8][7] + s[8][8]; 
        }
        if (sum == -1 || sum == 4 || sum == 9 || sum == 16 || sum == 25) {
            return true; 
        }
        //System.out.println(id + "  " + sum); 
        //printS(); 
        return false; 
    }
    
    //check if the new number at s[x][y] contradict anything
    //returns true if theres no contradiction
    public static boolean check (int id) {
        int[] coords = convert(id); 
        int row = coords[0]; 
        int col = coords[1]; 
        //System.out.println("row: " + coords[0] + "  col: " + coords[1]);
        
        //check in same row
        for (int i = 0; i < 9; i++) {
            if (s[row][i] == s[row][col] && i != col) {
                return false; 
            }
        }
        
        //check in same column
        for (int i = 0; i < 9; i++) {
            if (s[i][col] == s[row][col] && i != row) {
                return false; 
            }
        }
        
        //check in same box
        int boxNum = (row/3)*3 + col/3; 
        int[] b = box[boxNum];
        //System.out.println(b[3]); 
        for (int i = 0; i < 9; i++) {
            int[] bCoords = convert(b[i]); 
            if (s[bCoords[0]][bCoords[1]] == s[row][col] && !(row == bCoords[0] && col == bCoords[1])) {
                return false; 
            }
            //System.out.println(bCoords[0] + "   " + bCoords[1]);
        }
        
        return checkCage(id); 
    }
    
    //goes to the next position that is blank
    public static void nextPos () {
        pos++; 
        while (pos < 81 && s[cRow(pos)][cCol(pos)] != 0) {
            pos++; 
            if (pos == 81) {
                break; 
            }
        }
    }
    
    //goes to previoius position 
    public static void prevPos () {
        pos--; 
        while (pos > -1 && unsolved[cRow(pos)][cCol(pos)] != 0) {
            pos--; 
            if (pos == -1) {
                break; 
            }
        }
    }
    
    public static void fill () {
        if (pos > -1 && s[cRow(pos)][cCol(pos)] == 10) {
            s[cRow(pos)][cCol(pos)] = 0; 
            prevPos(); 
        } else {
            nextPos(); 
        }

        if (pos != 81 && pos != -1) {
            int rNum = cRow(pos); 
            int cNum = cCol(pos);
            s[rNum][cNum]++; 
            while (!check(pos) && s[rNum][cNum] <= 9) {
                s[rNum][cNum]++; 
            }
        }

        
    }
    
    public static void printS () {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(s[i][j] + " ");
            }
            System.out.println(); 
        }
        System.out.println(); 
    }
    
    public static void main(String[] args) {
        //set s as unsolved
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                s[i][j] = unsolved[i][j];
            }
        }

        System.out.println("Input: "); 
        printS(); 
        
        fill(); 
        while (pos < 81 && pos > -1) {
            fill(); 
            //printS(); 
        }
        if (pos == -1) {
            System.out.println("There are no solutions\n"); 
        } else {
            System.out.println("A solution is found");
            printS(); 
        }
    }
}
