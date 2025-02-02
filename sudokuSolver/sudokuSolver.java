class Main {
    //inputs
    /*static int[][] s = { 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    }; */
    
    static int pos = 0; 
    
    static int[][] unsolved = { 
        {0, 6, 0, 2, 0, 9, 0, 0, 0}, 
        {0, 0, 0, 0, 3, 0, 0, 1, 0}, 
        {1, 0, 0, 6, 0, 0, 0, 0, 9}, 
        {4, 2, 0, 5, 0, 0, 0, 9, 0}, 
        {0, 0, 5, 3, 0, 2, 8, 6, 0}, 
        {0, 8, 3, 1, 0, 0, 0, 2, 4}, 
        {8, 7, 0, 9, 0, 6, 0, 3, 5}, 
        {3, 4, 0, 0, 5, 0, 2, 7, 0}, 
        {2, 0, 6, 0, 7, 3, 0, 0, 1}, 
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
    /*static int[][] box = {
        { 0,  1,  2, 10, 11, 12, 20, 21, 22}, 
        { 3,  4,  5, 13, 14, 15, 23, 24, 25}, 
        { 6,  7,  8, 16, 17, 18, 26, 27, 28}, 
        {30, 31, 32, 40, 41, 42, 50, 51, 52}, 
        {33, 34, 35, 43, 44, 45, 53, 54, 55}, 
        {36, 37, 38, 46, 47, 48, 56, 57, 58}, 
        {60, 61, 62, 70, 71, 72, 80, 81, 82}, 
        {63, 64, 65, 73, 74, 75, 83, 84, 85}, 
        {66, 67, 68, 76, 77, 78, 86, 87, 88}, 
    };*/ 
    
    //answer
    /*
    answer: 
    7 6 4 2 1 9 3 5 8
    5 9 8 7 3 4 6 1 2
    1 3 2 6 8 5 7 4 9 
    4 2 7 5 6 8 1 9 3
    9 1 5 3 4 2 8 6 7
    8 7 1 9 2 6 4 3 5
    3 4 9 8 5 1 2 7 6
    2 5 6 4 7 3 9 8 1
    */
    
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
    
    //check if the new number at s[x][y] contradict anything
    //returns true if theres no contradiction
    public static boolean check (int id) {
        int[] coords = convert(id); 
        int row = coords[0]; 
        int col = coords[1]; 
        System.out.println("row: " + coords[0] + "  col: " + coords[1]);
        
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
        System.out.println(b[3]); 
        for (int i = 0; i < 9; i++) {
            int[] bCoords = convert(b[i]); 
            if (s[bCoords[0]][bCoords[1]] == s[row][col] && !(row == bCoords[0] && col == bCoords[1])) {
                return false; 
            }
            //System.out.println(bCoords[0] + "   " + bCoords[1]);
        }
        
        return true; 
    }
    
    public static void main(String[] args) {
        //set s as unsolved
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                s[i][j] = unsolved[i][j];
            }
        }
        
        //System.out.println(s[2][1]);
        System.out.println(check(19));
        //System.out.println(check(1, 1));
        //System.out.println(check(1, 2));
    }
}
