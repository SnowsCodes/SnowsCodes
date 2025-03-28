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
        
        b.print(); 
        
        //actual playing the game
        while (b.getMoveNum() < 42 && !b.getGameEnd()) {
            if (b.getMoveNum()%2 == 0) {
                System.out.println("It is player one's turn! "); 
            } else {
                System.out.println("It is player two's turn! ");
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
        if (board.get(col).size() < 6) {
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
        int len = board.get(c).size(); 
        //check horizontal
        int left = c, right = c, i = c; 
        while (i >= 0) {
            if (board.get(i).size() >= len && board.get(i).get(len-1) == board.get(c).get(len-1)) {
                i--; 
            } else {
                break; 
            }
        }
        left = i+1; 
        i = c; 
        while (i < 7) {
            if (board.get(i).size() >= len && board.get(i).get(len-1) == board.get(c).get(len-1)) {
                i++; 
            } else {
                break; 
            }
        }
        right = i-1; 
        //System.out.println(right + "  " + left);
        if (right - left >= 3) {
            return true; 
        }
        
        //check vertical
        int bottom = len-2; 
        while (bottom >= 0) {
            if (board.get(c).get(bottom) == board.get(c).get(len-1)) {
                bottom--; 
            } else {
                break; 
            }
        }
        if (len - bottom - 1 >= 4) {
            return true; 
        }
        
        //check diagonal /
        left = c;
        right = c; 
        int col = c, row = len-1; 
        while (col >= 0 && row >= 0 && board.get(col).size() > row) {
            if (board.get(col).get(row) == board.get(c).get(len-1)) {
                col--; 
                row--; 
            } else {
                break; 
            }
        }
        left = col+1; 
        col = c; 
        row = len-1; 
        while (col < 7 && row < 6 && board.get(col).size() > row) {
            if (board.get(col).get(row) == board.get(c).get(len-1)) {
                col++; 
                row++; 
            } else {
                break; 
            }
        }
        right = col-1; 
        if (right - left >= 3) {
            return true; 
        }
        
        //check diagonal \
        left = c;
        right = c; 
        col = c;
        row = len-1; 
        while (col >= 0 && row < 6 && board.get(col).size() > row) {
            if (board.get(col).get(row) == board.get(c).get(len-1)) {
                col--; 
                row++; 
            } else {
                break; 
            }
        }
        left = col+1; 
        col = c; 
        row = len-1; 
        while (col < 7 && row >= 0 && board.get(col).size() > row) {
            if (board.get(col).get(row) == board.get(c).get(len-1)) {
                col++; 
                row--; 
            } else {
                break; 
            }
        }
        right = col-1; 
        if (right - left >= 3) {
            return true; 
        }
        
        return false; 
    }

    public void print() {
        System.out.println(); 
        for (int row = 5; row >= 0; row--) {
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
