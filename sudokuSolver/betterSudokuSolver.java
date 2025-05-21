import java.util.Scanner; 

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][][] possible = new int[9][9][9]; 
        int[][] sudoku = new int[9][9]; 
        int[][][] block = {{{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}}, /**/ {{0, 3}, {0, 4}, {0, 5}, {1, 3}, {1, 4}, {1, 5}, {2, 3}, {2, 4}, {2, 5}}, /**/ {{0, 6}, {0, 7}, {0, 8}, {1, 6}, {1, 7}, {1, 8}, {2, 6}, {2, 7}, {2, 8}}, /**/ {{3, 0}, {3, 1}, {3, 2}, {4, 0}, {4, 1}, {4, 2}, {5, 0}, {5, 1}, {5, 2}}, /**/ {{3, 3}, {3, 4}, {3, 5}, {4, 3}, {4, 4}, {4, 5}, {5, 3}, {5, 4}, {5, 5}}, /**/ {{3, 6}, {3, 7}, {3, 8}, {4, 6}, {4, 7}, {4, 8}, {5, 6}, {5, 7}, {5, 8}}, /**/ {{6, 0}, {6, 1}, {6, 2}, {7, 0}, {7, 1}, {7, 2}, {8, 0}, {8, 1}, {8, 2}}, /**/ {{6, 3}, {6, 4}, {6, 5}, {7, 3}, {7, 4}, {7, 5}, {8, 3}, {8, 4}, {8, 5}}, /**/ {{6, 6}, {6, 7}, {6, 8}, {7, 6}, {7, 7}, {7, 8}, {8, 6}, {8, 7}, {8, 8}}};
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    possible[i][j][k] = k+1; 
                }
            }
        }
        
        for (int i = 0; i < 9; i++) {
            System.out.print("What is in row " + (i+1) + "? ");
            while (!in.hasNextInt()) {
                String a = in.next(); 
                System.out.print("Please only input integers. What is in row " + (i+1) + "? ");
            }
            
            int r = in.nextInt(); 
            System.out.println("For row " + (i+1) + ", you inputed " + r);
            
            for (int j = 8; j >= 0; j--) {
                if (possible[i][j][(r%10)-1] == 0) {
                    System.out.println("Sorry, the value at row " + i + " column " + j + " conflicts with a previous value inputted"); 
                    return; 
                } else {
                    sudoku[i][j] = r%10; 
                    
                    //only remove values if the value inputted isn't 0
                    if (r%10 != 0) {
                        //code to remove r%10 from rows, columns, and    blocks in possible
                        //remove from row
                        for (int c = 0; c < 9; c++) {
                            if (c != j) {
                                possible[i][c][(r%10)-1] = 0; 
                            }
                        }
                        //remove from column
                        for (int row = 0; row < 9; row++) {
                            if (row != i) {
                                possible[row][j][(r%10)-1] = 0; 
                            }
                        }
                        //remove from block
                        //b is which block it is
                        int b = (j/3)%3 + i/3;
                    }
                }
                r /= 10; 
            }
            
        }
    }
}
