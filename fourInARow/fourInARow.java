import java.util.*; 

class Main {
    public static void main (String[] args) {
        Scanner in = new Scanner(System.in); 
        Board b = new Board(false); 
        
        //checks if player wants to play with bot
        System.out.print("Do you want to play with a bot? ");
        String s = in.next().toUpperCase(); 
        if (s.equals("Y") || s.equals("YES")) {
            b = new Board(true); 
            System.out.println("You are now playing with a bot"); 
        } else {
            System.out.println("You are not playing with a bot");
        }

        //actual playing the game
        while (b.getMoveNum() < 42 && !b.getGameEnd()) {
            if (b.getMoveNum()%2 == 0) {
                System.out.println("It is player one's turn! "); 
            } else {
                System.out.println("Itis player two's turn! ");
            }
            System.out.print("Where would you like to move? "); 
            int col = -1; 
            while (col < 0 || col > 6) {
                System.out.println("Please enter an integer from 0 to 6 inclusive");
                if (in.hasNextInt()) {
                    col = in.nextInt(); 
                } 
            }
            b.move(col); 
        }
        if (b.getMoveNum() == 42) {
            System.out.println("It's a tie!"); 
        } else if (b.getMoveNum()%2 == 0){
            System.out.println("Player two wins!");
        } else {
            System.out.println("Player one wins!");
        }
        in.close(); 
    }
}

class Board {
    //instance variables
    private ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>(); 
    private boolean hasBot; 
    private int moveNum = 0; 
    private boolean gameEnd = false; 

    //constructors

    public Board (boolean h) {
        hasBot = h; 
        for (int i = 0; i < 7; i++) {
            board.add(new ArrayList<Integer>());
        }
    }

    //methods
    public void move (int col) {
        if (board.get(col).size() < 7) {
            if (moveNum%2 == 0) {
                board.get(col).add(0); 
            } else {
                board.get(col).add(1); 
            }
            moveNum++; 
            print(); 
        } else {
            System.out.println("This column is full already. Please enter another number"); 
        } 
        if (check(col)) {
            gameEnd = true; 
            return; 
        }
        if (hasBot) {
            int c = (int) (7 * Math.random()); 
            while (board.get(col).size() >= 7) {
                c = (int) (7 * Math.random()); 
            }
            board.get(c).add(1); 
            moveNum++; 
            print(); 
        }
        if (check(col)) {
            gameEnd = true; 
            return; 
        }
    }

    public boolean check (int c) {
        ArrayList<Integer> col = board.get(c); 
        int len = col.size(); 
        //check horizontal
        //checks for four in a rows where all the other columns are to the left of c
        if (c-3 > -1 && board.get(c-1).size() >= len && board.get(c-2).size() >= len && board.get(c-3).size() >= len) {
            if (col.get(len-1) == board.get(c-1).get(len-1) && col.get(len-1) == board.get(c-2).get(len-1) && col.get(len-1) == board.get(c-3).get(len-1)) {
                return true; 
            }
        }
        //checks for four in a rows where all but one of the other columns are to the left of c
        if (c-2 > -1 && c+1 < 7 && board.get(c-1).size() >= len && board.get(c-2).size() >= len && board.get(c+1).size() >= len) {
            if (col.get(len-1) == board.get(c-1).get(len-1) && col.get(len-1) == board.get(c-2).get(len-1) && col.get(len-1) == board.get(c+1).get(len-1)) {
                return true; 
            }
        }
        //checks for four in a rows where all but one of the other columns are to the right of c
        if (c-1 > -1 && c+2 < 7 && board.get(c-1).size() >= len && board.get(c+1).size() >= len && board.get(c+2).size() >= len) {
            if (col.get(len-1) == board.get(c-1).get(len-1) && col.get(len-1) == board.get(c+1).get(len-1) && col.get(len-1) == board.get(c+2).get(len-1)) {
                return true; 
            }
        }
        //checks for four in a rows where all other columns are to the right of c
        if (c+3 < 7 && board.get(c+1).size() >= len && board.get(c+2).size() >= len && board.get(c+3).size() >= len) {
            if (col.get(len-1) == board.get(c+1).get(len-1) && col.get(len-1) == board.get(c+2).get(len-1) && col.get(len-1) == board.get(c+3).get(len-1)) {
                return true; 
            }
        }

        //check vertical
        if (len >= 4) {
            if (col.get(len-1) == col.get(len-2) && col.get(len-1) == col.get(len-3) && col.get(len-1) == col.get(len-4)) {
                return true; 
            }
        }

        //check diagonal /
        if (c-3 > -1 && len-3 > 0 && board.get(c-1).size() >= len-1 && board.get(c-2).size() >= len-2 && board.get(c-3).size() >= len-3) {
            if (col.get(len-1) == board.get(c-1).get(len-2) && col.get(len-1) == board.get(c-2).get(len-3) && col.get(len-1) == board.get(c-3).get(len-4)) {
                return true; 
            }
        }
        if (c-2 > -1 && c+1 < 7 && len-2 > 0 && len+1 <= 6 && board.get(c-1).size() >= len-1 && board.get(c-2).size() >= len-2 && board.get(c+1).size() >= len+1) {
            if (col.get(len-1) == board.get(c-1).get(len-2) && col.get(len-1) == board.get(c-2).get(len-3) && col.get(len-1) == board.get(c+1).get(len)) {
                return true; 
            }
        }
        if (c-1 > -1 && c+2 < 7 && len-1 > 0 && len+2 <= 6 && board.get(c-1).size() >= len-1 && board.get(c+1).size() >= len+1 && board.get(c+2).size() >= len+2) {
            if (col.get(len-1) == board.get(c-1).get(len-2) && col.get(len-1) == board.get(c+1).get(len) && col.get(len-1) == board.get(c+2).get(len+1)) {
                return true; 
            }
        }
        if (c+3 > 7 && len+3 <= 6 && board.get(c+1).size() >= len+1 && board.get(c+2).size() >= len+2 && board.get(c+3).size() >= len+3) {
            if (col.get(len-1) == board.get(c+1).get(len) && col.get(len-1) == board.get(c+2).get(len+1) && col.get(len-1) == board.get(c+3).get(len+2)) {
                return true; 
            }
        }

        //check diagonal \
        if (c-3 > -1 && len+3 <= 6 && board.get(c-1).size() >= len+1 && board.get(c-2).size() >= len+2 && board.get(c-3).size() >= len+3) {
            if (col.get(len-1) == board.get(c-1).get(len) && col.get(len-1) == board.get(c-2).get(len+1) && col.get(len-1) == board.get(c-3).get(len+2)) {
                return true; 
            }
        }
        if (c-2 > -1 && c+1 < 7 && len-2 > 0 && len+1 <= 6 && board.get(c-1).size() >= len+1 && board.get(c-2).size() >= len+2 && board.get(c+1).size() >= len-1) {
            if (col.get(len-1) == board.get(c-1).get(len) && col.get(len-1) == board.get(c-2).get(len+1) && col.get(len-1) == board.get(c+1).get(len-2)) {
                return true; 
            }
        }
        if (c-1 > -1 && c+2 < 7 && len-1 > 0 && len+2 <= 6 && board.get(c-1).size() >= len+1 && board.get(c+1).size() >= len-1 && board.get(c+2).size() >= len-2) {
            if (col.get(len-1) == board.get(c-1).get(len) && col.get(len-1) == board.get(c+1).get(len-2) && col.get(len-1) == board.get(c+2).get(len-3)) {
                return true; 
            }
        }
        if (c+3 > 7 && len+3 <= 6 && board.get(c+1).size() >= len-1 && board.get(c+2).size() >= len-2 && board.get(c+3).size() >= len-3) {
            if (col.get(len-1) == board.get(c+1).get(len-2) && col.get(len-1) == board.get(c+2).get(len-3) && col.get(len-1) == board.get(c+3).get(len-4)) {
                return true; 
            }
        }

        return false; 
    }

    public void print() {
        System.out.println(); 
        for (int row = 6; row >= 0; row--) {
            for (int col = 0; col < 7; col++) {
                if (row < board.get(col).size()) {
                    System.out.print(board.get(col).get(row) + " "); 
                } else {
                    System.out.print("- "); 
                }
            }
            System.out.println(); 
        }
    }

    public int getMoveNum () {
        return moveNum; 
    }

    public boolean getGameEnd() {
        return gameEnd; 
    }
}
