import java.util.Scanner; 

class Main {
    /*inputs: 
     *   5020093
     * 420010060
     *    600500
     *  47000600
     *     30749
     * 100760358
     *   9870430
     *   8040076
     * 730050981
    */
    
    static int[][][] possible = new int[9][9][9]; 
    static int[][] sudoku = new int[9][9]; 
    static int[][][] block = {{{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}}, /**/ {{0, 3}, {0, 4}, {0, 5}, {1, 3}, {1, 4}, {1, 5}, {2, 3}, {2, 4}, {2, 5}}, /**/ {{0, 6}, {0, 7}, {0, 8}, {1, 6}, {1, 7}, {1, 8}, {2, 6}, {2, 7}, {2, 8}}, /**/ {{3, 0}, {3, 1}, {3, 2}, {4, 0}, {4, 1}, {4, 2}, {5, 0}, {5, 1}, {5, 2}}, /**/ {{3, 3}, {3, 4}, {3, 5}, {4, 3}, {4, 4}, {4, 5}, {5, 3}, {5, 4}, {5, 5}}, /**/ {{3, 6}, {3, 7}, {3, 8}, {4, 6}, {4, 7}, {4, 8}, {5, 6}, {5, 7}, {5, 8}}, /**/ {{6, 0}, {6, 1}, {6, 2}, {7, 0}, {7, 1}, {7, 2}, {8, 0}, {8, 1}, {8, 2}}, /**/ {{6, 3}, {6, 4}, {6, 5}, {7, 3}, {7, 4}, {7, 5}, {8, 3}, {8, 4}, {8, 5}}, /**/ {{6, 6}, {6, 7}, {6, 8}, {7, 6}, {7, 7}, {7, 8}, {8, 6}, {8, 7}, {8, 8}}};
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        //initialize possible
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    possible[i][j][k] = k+1; 
                }
            }
        }
        
        //fill in initial values for sudoku and update possible
        //uncomment for loop after finishing program project
        /*for (int i = 0; i < 9; i++) {
            System.out.print("What is in row " + (i+1) + "? ");
            while (!in.hasNextInt()) {
                String a = in.next(); 
                System.out.print("Please only input integers. What is in row " + (i+1) + "? ");
            }
            
            int r = in.nextInt(); 
            //System.out.println("For row " + (i+1) + ", you inputed " + r);
            
            for (int j = 8; j >= 0; j--) {
                if (r%10 != 0 && possible[i][j][(r%10)-1] == 0) {
                    System.out.println("Sorry, the value at row " + i + " column " + j + " conflicts with a previous value inputted"); 
                    return; 
                } else {
                    sudoku[i][j] = r%10; 
                    //only remove values if the input isn't 0
                    if (r%10 != 0) {
                        removeVals(r%10, i, j); 
                    }
                }
                r /= 10; 
            }
            
        }*/
        
        //delete this for loop after finishign program (this inputs a test case)
        int[] inputs = {5020093, 420010060, 600500, 47000600, 30749, 100760358, 9870430, 8040076, 730050981}; 
        for (int i = 0; i < 9; i++) {
            int r = inputs[i]; 
            
            for (int j = 8; j >= 0; j--) {
                if (r%10 != 0 && possible[i][j][(r%10)-1] == 0) {
                    System.out.println("Sorry, the value at row " + i + " column " + j + " conflicts with a previous value inputted"); 
                    return; 
                } else {
                    sudoku[i][j] = r%10; 
                    //only remove values if the input isn't 0
                    if (r%10 != 0) {
                        removeVals(r%10, i, j); 
                    }
                }
                r /= 10; 
            }
            
        }
        
        System.out.println("\nThis is what you have inputted: "); 
        print(); 
        System.out.println("Is this correct?"); 
        String c = in.next().toUpperCase();
        
        //System.out.println("You have entered: " + c);
        if (c.equals("Y") || c.equals("YES")) {
            System.out.println("\nYou entered yes. Please wait as the program solves the sudoku\n");
        } else  {
            System.out.println("\nYou entered no. Please restart the program to input different values");
            return; 
        } 
        
        for (int i = 0; i < 9; i++) {
            printP(i); 
            System.out.println("\n");
        }
        
    }
    
    //prints sudoku
    public static void print () {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println(); 
        }
    } 
    
    //prints possible
    public static void printP (int val) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(possible[i][j][val] + " ");
            }
            System.out.println(); 
        }
    }
    
    //removes val from same row, col, and block
    public static void removeVals (int val, int row, int col) {
        //remove all vals from current position 
        for (int i = 0; i < 9; i++) {
            possible[row][col][i] = 0; 
        }
        
        //remove from row
        for (int c = 0; c < 9; c++) {
            if (c != col) {
                possible[row][c][val-1] = 0; 
            }
        }
        
        //remove from col
        for (int r = 0; r < 9; r++) {
            if (r != row) {
                possible[r][col][val-1] = 0; 
            }
        }
        
        //remove from block
        int b = (col/3)%3 + row/3; 
        for (int i = 0; i < 9; i++) {
            int r = block[b][i][0]; 
            int c = block[b][i][1]; 
            if (r != row || c != col) {
                possible[r][c][val-1] = 0; 
            }
        }
        
    }
}
