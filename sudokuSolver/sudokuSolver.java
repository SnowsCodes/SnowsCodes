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
        {1, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 1, 0, 0, 0, 0, 0, 0, 0}, 
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
        { 0,  1,  2, 10, 11, 12, 20, 21, 22}, 
        { 3,  4,  5, 13, 14, 15, 23, 24, 25}, 
        { 6,  7,  8, 16, 17, 18, 26, 27, 28}, 
        {30, 31, 32, 40, 41, 42, 50, 51, 52}, 
        {33, 34, 35, 43, 44, 45, 53, 54, 55}, 
        {36, 37, 38, 46, 47, 48, 56, 57, 58}, 
        {60, 61, 62, 70, 71, 72, 80, 81, 82}, 
        {63, 64, 65, 73, 74, 75, 83, 84, 85}, 
        {66, 67, 68, 76, 77, 78, 86, 87, 88}, 
    }; 
    
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
    
    public static int[] convert (int in) {
        return new int[] {in/10, in%10}; 
    }
    
    public static int convertX (int in) {
        return in/10; 
    }
    
    //check if the new number at s[x][y] contradict anything
    //returns true if theres no contradiction
    public static boolean check (int x, int y) {
        //check the row (constant y value)
        for (int i = 0; i < 9; i++) {
            if (s[i][y] == s[x][y] && i != x) {
                return false; 
            }
        }
        //check the column (constant x value)
        for (int i = 0; i < 9; i++) {
            if (s[x][i] == s[x][y] && i != y) {
                return false; 
            }
        }
        
        //check box
        int boxNum = (x/3)*3 + y/3; 
        int[] b = box[boxNum];
        for (int i = 0; i < 9; i++) {
            //stands for check x and check y; 
            //int cX
            if (s[b[i]/10][b[i]%10] == s[x][y] && s[x][y] != 0 && s[b[i]/10][b[i]%10] != 0 &&(b[i]/10 != x || b[i]%10 != y)) {
                return false; 
            }
        }
        
        return true; 
    }
    
    public static void main(String[] args) {
        //set s as unsolved
        /*for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                s[i][j] = unsolved[i][j];
            }
        }*/
        
        
        System.out.println(check(1, 1));
        System.out.println(check(1, 2));
    }
}
