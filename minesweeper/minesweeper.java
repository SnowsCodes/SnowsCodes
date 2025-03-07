//current errors: 
//no none errors

import java.util.Scanner; 
import java.util.ArrayList; 

class Main {
    public static void main (String[] args) {
        Minesweeper m = new Minesweeper();
        Scanner in = new Scanner(System.in);
        
        //explanation of game
        System.out.println("How to play this game: "); 
        System.out.println("When entering an input, the first number has to be a 0, 1, or 2");
        System.out.println("A 0 means you are revealing a spot, a 1 means you are marking a spot, and a 2 means you are unmarking a spot");
        System.out.println("The second input is a letter corresponding to the column it's in");
        System.out.println("The third input is a number corresponding to the row it's in\n");
        System.out.println("What size board do you want? Type in large if you want a large board. Type in small if you want a small board. If you want a custom sized board, enter two numbers separated by a space, with the first one being the width of the board. Type in anything else for a medium sized board.\n");
        
        //generates a map either with two inputted numbers or an inputted string
        boolean gen = false; 
        if (in.hasNextInt()) {
            int width = in.nextInt(); 
            if (in.hasNextInt()) {
                m = new Minesweeper(width, in.nextInt());
                gen = true; 
            } 
        } 
        if (!gen) {
            m = new Minesweeper(in.next()); 
        }
        m.printR(); 
        System.out.println("A " + m.getW() + " by " + m.getH() + " board has been generated!");
        
        //runs the actual game
        //while the game hasn't ended, ask the player for an input for a move
        while (!m.getGameEnd() && m.getLeft() > 0) {
            System.out.println("Enter your next move: ");
            String input1; 
            String input2; 
            int input3; 
            if (in.hasNext()) {
                input1 = in.next(); 
            } else {
                System.out.println("ERROR -- please enter an integer for the first input");
                continue; 
            }
            if (in.hasNext()) {
                input2 = in.next(); 
            } else {
                System.out.println("ERROR -- please enter 3 different inputs separated by a space");
                continue; 
            }
            if (in.hasNext()) {
                if (in.hasNextInt()) {
                    input3 = in.nextInt(); 
                } else {
                    System.out.println("ERROR -- please enter an integer for the third input");
                    continue; 
                }
            } else {
                System.out.println("ERROR -- please enter 3 different inputs separated by a space");
                continue; 
            }
            if (input1.equals("0")) {
                m.move(input2, input3);
            } else if (input1.equals("1")) {
                m.mark(input2, input3); 
            } else if (input1.equals("2")) {
                m.unmark(input2, input3); 
            } else {
                System.out.println("ERROR -- the first number has to be an integer from 0 to 2 inclusive");
            }
        }
        
        //if there are no positions left to reveal, print you win
        if (m.getLeft() == 0) {
            System.out.println("YOU WIN!");
        }
        in.close();  
    }
}

class Minesweeper {
    //static variables
    private static String[] abcs = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    //instance variables
    private int h; 
    private int w; 
    //map of mines
    private int[][] m; 
    //map of numbers of mines surrounding each cell
    private int[][] n; 
    //revealed values
    private String[][] r; 
    //number of non-bomb spaces left
    private int left = 0; 
    private boolean gameEnd = false; 
    private boolean isFirst = true; 

    //constructors
    //default = medium: h = 14, w = 18
    //small: h = 8, w = 10
    //large:  h = 20, w = 24
    Minesweeper () {
        this(14, 18);
    }

    Minesweeper (String size) {
        String s = size.toUpperCase(); 
        if (s.equals("LARGE")) {
            h = 20; 
            w = 24; 
        } else if (s.equals("SMALL")) {
            h = 8; 
            w = 10; 
        } else {
            h = 14; 
            w = 18; 
        }
        genMap(); 
    }

    Minesweeper (int w, int h) { 
        this.h = h;
        this.w = w; 

        genMap(); 
    }

    //methods
    
    //generate a map for the mines
    private void genMap () {
        //15% of the board are mines
        int total = w * h; 
        int numMines = total/5; 
        m = new int[h][w];
        n = new int[h][w]; 

        //generate map of mines
        for (int i = 0; i < numMines; i++) {
            int id = (int) (total * Math.random()); 
            m[id/w][id%w] = 1;
        }

        //generate map of numbers
        for (int i = 0; i < total; i++) {
            int row = i/w; 
            int col = i%w; 
            if (m[row][col] == 1) {
                n[row][col] = 9; 
            } else {
                int count = 0; 

                if (row-1 > -1) {
                    if (col-1 > -1 && m[row-1][col-1] == 1) {
                        count++; 
                    }
                    if (m[row-1][col] == 1) {
                        count++; 
                    }
                    if (col+1 < w && m[row-1][col+1] == 1) {
                        count++; 
                    }
                }
                if (row+1 < h) {
                    if (col-1 > -1 && m[row+1][col-1] == 1) {
                        count++; 
                    }
                    if (m[row+1][col] == 1) {
                        count++; 
                    }
                    if (col+1 < w && m[row+1][col+1] == 1) {
                        count++; 
                    }
                }
                if (col-1 > -1 && m[row][col-1] == 1) {
                    count++; 
                }
                if (col+1 < w && m[row][col+1] == 1) {
                    count++; 
                }

                n[row][col] = count; 
            }
        }

        //set all values of n at a mine as 9 
        for (int i = 0; i < total; i++) {
            if (m[i/w][i%w] == 1) {
                n[i/w][i%w] = 9; 
            }
        }

        //set all values in r as "-" and count the number of spaces that are not mines
        r = new String[h][w]; 
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r[0].length; j++) {
                r[i][j] = "-"; 
                if (m[i][j] != 1) {
                    left++; 
                }
            }
        }
    }
    
    //prints the revealed board
    public void printR() {
        System.out.print("    ");
        if (h > 10) {
            System.out.print(" ");
        }
        for (int i = 0; i < w; i++) {
            System.out.print(abcs[i] + " ");
        } 
        
        String out = ""; 
        for (int i = 0; i < h; i++) {
            if (h >= 10 && i < 10) {
                out += "\n" + i + "  | ";
            } else {
                out += "\n" + i + " | ";
            }
            for (int j = 0; j < w; j++) {
                out += r[i][j] + " ";
            }
            out += " | " + i; 
        }
        System.out.println(out); 
        
        
        System.out.print("    ");
        if (h > 10) {
            System.out.print(" ");
        }
        for (int i = 0; i < w; i++) {
            System.out.print(abcs[i] + " ");
        }
        System.out.println(); 
    }

    //reveals a tile
    public void move (String c, int row) {
        c = c.toUpperCase(); 
        int col = -1; 
        for (int i = 0; i < w; i++) {
            if (abcs[i].equals(c)) {
                col = i; 
            }
        }
        
        if (isFirst) {
            isFirst = false; 
            while (n[row][col] != 0) {
                genMap(); 
            }
        }
        
        if (col < 0 || row < 0 || row >= h) {
            System.out.println("Please try again, this position does not exist");
            return; 
        }

        if (r[row][col].equals("!")) {
            System.out.println("Please try again, this position has been marked as a mine");
            return; 
        } else if (!r[row][col].equals("-")) {
            System.out.println("Please try again, this position has already been revealed");
            return; 
        } 

        if (m[row][col] == 1) {
            System.out.println("YOU LOST! \nThis spot is a mine!");
            gameEnd = true; 
        } else {
            r[row][col] = "" + n[row][col]; 
            left--; 
            if (n[row][col] == 0) {
                //System.out.println("ZEROOOO");
                revealZeroes(row * w + col);
            }
            //System.out.println("number left: " + left);
            printR(); 
            System.out.println("Number revealed");
        }
    }
    
    //reveals all numbers surrounding zero
    public void revealZeroes (int id) {
        ArrayList<Integer> list = new ArrayList<Integer>(); 
        list.add(id); 
        //find all the values to be revealed
        for (int i = 0; i < list.size(); i++) {
            //if the value at i is zero, add all surrounding s
            //for each value in s, if list does not contain it, add it to list
            int c = list.get(i); 
            //System.out.println("c: " + c + "    " + abcs[c%w] + c/w);
            //System.out.println(c/w + " " + c%w + "  " + n[c/w][c%w] + "  " + (n[c/w][c%w] == 0));
            if (n[c/w][c%w] == 0) {
                int[] s = new int[8]; 
                s[0] = c - w - 1; 
                s[1] = c - w; 
                s[2] = c - w + 1; 
                s[3] = c - 1; 
                s[4] = c + 1; 
                s[5] = c + w - 1; 
                s[6] = c + w; 
                s[7] = c + w + 1; 
                int len = list.size(); 
                
                for (int j = 0; j < 8; j++) {
                    if (s[j] < 0 || s[j] >= w*h) {
                        s[j] = c; 
                    } 
                    if ((j == 0 || j == 2) && s[j]/w != (c/w)-1) {
                        s[j] = c; 
                    } else if ((j == 5 || j == 7) && s[j]/w != (c/w)+1) {
                        s[j] = c; 
                    } else if ((j == 3 || j == 4) && s[j]/w != c/w) {
                        s[j] = c; 
                    }
                }
                
                //System.out.println(abcs[s[0]%w] + s[0]/w + "   " + abcs[s[1]%w] + s[1]/w + "   " + abcs[s[2]%w] + s[2]/w + "   " + abcs[s[3]%w] + s[3]/w + "   " + abcs[s[4]%w] + s[4]/w + "   " + abcs[s[5]%w] + s[5]/w + "   " + abcs[s[6]%w] + s[6]/w + "   " + abcs[s[7]%w] + s[7]/w);
                
                
                for (int j = 0; j < 8; j++) {
                    boolean dupe = false; 
                    for (int k = 0; k < len; k++) {
                        if (s[j] == list.get(k)) {
                            dupe = true; 
                            break; 
                        }
                    }
                    if (!dupe) {
                        list.add(s[j]); 
                    }
                }
            }
        }
        //reveal all values
        for (int i = 0; i < list.size(); i++) {
            int p = list.get(i); 
            if (r[p/w][p%w].equals("-")) {
                r[p/w][p%w] = "" + n[p/w][p%w]; 
            }
        }
        
        //recount left
        int count = 0; 
        for (int i = 0; i < w*h; i++) {
            if (r[i/w][i%w].equals("-") && n[i/w][i%w] < 9) {
                count++; 
            }
        }
        left = count; 
    }

    public void mark (String c, int row) {
        c = c.toUpperCase(); 
        int col = -1; 
        for (int i = 0; i < w; i++) {
            if (abcs[i].equals(c)) {
                col = i; 
            }
        }
        if (col < 0 || row < 0 || row >= h) {
            System.out.println("Please try again, this position does not exist");
            return; 
        }

        if (r[row][col].equals("!")) {
            System.out.println("Please try again, this position has been marked as a bomb");
            return; 
        } else if (!r[row][col].equals("-")) {
            System.out.println("Please try again, this position has already been revealed");
            return; 
        } 

        r[row][col] = "!";  
        printR(); 
        System.out.println("Spot marked");
    }

    public void unmark (String c, int row) {
        c = c.toUpperCase(); 
        int col = -1; 
        for (int i = 0; i < w; i++) {
            if (abcs[i].equals(c)) {
                col = i; 
            }
        }

        if (col < 0 || row < 0 || row >= h) {
            System.out.println("Please try again, this position does not exist");
            return; 
        }

        if (r[row][col].equals("!")) {
            System.out.println("Spot unmarked"); 
            r[row][col] = "-"; 
            printR(); 
        } else if (r[row][col].equals("-")) {
            System.out.println("Please try again, this position hasn't been marked yet");
            return; 
        } else {
            System.out.println("Please try again, this position has already been revealed");
            return; 
        } 
    }

    public int getLeft () {
        return left; 
    }
    
    public boolean getGameEnd () {
        return gameEnd; 
    }
    
    public int getW () {
        return w; 
    }
    
    public int getH () {
        return h; 
    }
}
