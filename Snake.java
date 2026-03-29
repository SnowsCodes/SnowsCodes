import java.util.ArrayList; 
import java.util.Random; 
import java.time.LocalTime;

class Board {
    //static variables
    //0 = empty
    //1-6 = body
    //7-10 = head
    //11 = apple
    private static String[] sParts = new String[]{". ", "─ ", "│ ", "┌ ", "┐ ", "└ ", "┘ ", "V ", "< ", "Λ ", "> ", "🍎"}; 

    //instance variables
    private int numRow; 
    private int numCol; 
    private int[][] b; 
    private Snake s; 
    private int[] apple; 
    private boolean gameEnd = false; 

    private ArrayList<int[]> path; 
    private ArrayList<Integer> pDirs; 

    //constructor
    public Board (int r, int c) {
        numRow = r; 
        numCol = c; 
        int xPos = (int) Math.ceil(c / 4.0); 
        int yPos = r / 2; 

        //finds the starting position of the snake and apple
        s = new Snake(new int[][]{{yPos, xPos}, {yPos, xPos-1}, {yPos, xPos-2}, {yPos, xPos-3}}); 
        //s = new Snake(new int[][]{{1, 0}, {0, 0}}); 
        apple = new int[]{yPos, (c - xPos)}; 

        updateBoard(); 
        print(); 

        //s.print(); 
    }

    public void print () {
        //this for pathfinding
        findPath(); 


        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                int val = b[i][j]; 
                System.out.print(sParts[val]); 
            }
            System.out.println(); 
        }
        System.out.println(); 
    }

    private void updateBoard () {
        b = new int[numRow][numCol]; 
        ArrayList<int[]> pos = s.getPos(); 
        for (int i = 0; i < pos.size(); i++) {
            int val = 0; 

            int[] p = pos.get(i); 
            if (i == 0) {
                int[] n = pos.get(i+1); 
                if (p[0] == n[0] && p[1] > n[1]) {
                    val = 10; 
                } else if (p[0] == n[0] && p[1] < n[1]) {
                    val = 8; 
                } else if (p[0] > n[0] && p[1] == n[1]) {
                    val = 7; 
                } else {
                    val = 9; 
                }
            } else if (i == pos.size() - 1) {
                int[] b = pos.get(i-1); 
                if (p[0] == b[0]) {
                    val = 1; 
                } else {
                    val = 2; 
                }
            } else {
                int[] b = pos.get(i-1); 
                int[] n = pos.get(i+1); 

                if (b[0] == n[0]) {
                    val = 1; 
                } else if (b[1] == n[1]) {
                    val = 2; 
                } else if (b[0] < p[0] || n[0] < p[0]) {
                    //connects above
                    if (b[1] < p[1] || n[1] < p[1]) {
                        //connects left
                        val = 6; 
                    } else {
                        //connects right
                        val = 5; 
                    }
                } else {
                    //connects below
                    if (b[1] < p[1] || n[1] < p[1]) {
                        //connects left
                        val = 4; 
                    } else {
                        //connects right
                        val = 3; 
                    }
                }
            }

            b[p[0]][p[1]] = val; 
        }
        b[apple[0]][apple[1]] = 11; 
    }

    public void next () {
        //if the game is over, say so
        if (gameEnd) {
            System.out.println("The game is over."); 
            return; 
        }
        //moves snake to next state, and stores whether or not the apple is eaten
        boolean eatApple = s.next(apple); 
        if (eatApple) {
            System.out.println("The apple got eaten!"); 
        }

        //checks if the snake hits the wall
        int[] h = s.getHead(); 
        if (h[0] < 0 || h[0] >= numRow || h[1] < 0 || h[1] >= numCol) {
            gameEnd = true; 
            System.out.println("Uh oh! You hit a wall. "); 
            return; 
        }
        //checks if snake hits itself
        ArrayList<int[]> snakePos = s.getPos(); 
        for (int i = 1; i < snakePos.size(); i++) {
            int[] body = snakePos.get(i); 
            if (body[0] == h[0] && body[1] == h[1]) {
                gameEnd = true; 
                System.out.println("Uh oh! You hit yourself. "); 
                return; 
            }
        }

        //if the apple is eaten, randomly choose a new spot for the apple
        while (eatApple) {
            apple[0] = (int) (Math.random() * numRow); 
            apple[1] = (int) (Math.random() * numCol); 
            //check if the snake is on the apple. if no, break the while loop
            if (!s.contains(apple)) {
                break; 
            }
        }

        updateBoard(); 
    }

    public boolean gameEnded () {
        return gameEnd; 
    }

    public void findPath () {
        ArrayList<int[]> snakePos = s.getPos(); 
        //creates a deep copy
        ArrayList<int[]> currentPath = new ArrayList<>(); 
        for (int[] p : snakePos) {
            currentPath.add(new int[]{p[0], p[1]}); 
        }
        int startLen = currentPath.size(); 
        ArrayList<Integer> directions = new ArrayList<>(); 
        boolean backtrack = false; 
        //int[] head = currentPath.get(0); 

        //while (true) {
            int[] head = currentPath.get(0); 
            if (backtrack) {
                head = currentPath.get(1); 
            }
            int[][] priority = new int[3][2]; 
            int[] dirs; 
            
            //find where each direction goes
            int d = s.getDir();
            priority[0] = calcNext(head, d); 
            priority[1] = calcNext(head, (d+1)%4); 
            priority[2] = calcNext(head, (d+3)%4); 

            int[] dists = new int[3]; 
            int ogDist = calcDist(head, apple); 
            dists[0] = calcDist(priority[0], apple) - ogDist; 
            dists[1] = calcDist(priority[1], apple) - ogDist; 
            dists[2] = calcDist(priority[2], apple) - ogDist; 

            if (dists[0] < 0 || (dists[0] < dists[1] && dists[0] < dists[2]))  {
                if (dists[2] < dists[1]) {
                    dirs = new int[]{0, 2, 1}; 
                    int[] temp = new int[]{priority[2][0], priority[2][1]}; 
                    priority[2] = priority[1]; 
                    priority[1] = temp; 
                } else {
                    dirs = new int[]{0, 1, 2}; 
                }
            } else if (dists[1] < dists[0] && dists[1] < dists[2]) {
                //first should be 1
                if (dists[0] < dists[2]) {
                    //order: 1 0 2
                    dirs = new int[]{1, 0, 2}; 
                    int[] temp = new int[]{priority[0][0], priority[0][1]}; 
                    priority[0] = priority[1]; 
                    priority[1] = temp; 
                } else {
                    //order: 1 2 0
                    dirs = new int[]{1, 2, 0}; 
                    int[] temp = new int[]{priority[0][0], priority[0][1]}; 
                    priority[0] = priority[1]; 
                    priority[1] = priority[2]; 
                    priority[2] = temp; 
                }
            } else {
                //first should be 2
                if (dists[0] < dists[1]) {
                    //order: 2 0 1
                    dirs = new int[]{2, 0, 1}; 
                    int[] temp = new int[]{priority[0][0], priority[0][1]}; 
                    priority[0] = priority[2]; 
                    priority[2] = priority[1]; 
                    priority[1] = temp; 
                } else {
                    //order: 2 1 0
                    dirs = new int[]{2, 1, 0}; 
                    int[] temp = new int[]{priority[0][0], priority[0][1]}; 
                    priority[0] = priority[2]; 
                    priority[2] = temp; 
                }
            }

            System.out.printf("current: %d, %d\nbest: %d, %d\t2nd: %d, %d\t3rd: %d, %d\n", head[1], head[0], priority[0][1], priority[0][0], priority[1][1], priority[1][0], priority[2][1], priority[2][0]); 

            if (!backtrack) {
                s.changeDir(dirs[0]); 
            }
        //}
    }

    private int[] calcNext (int[] start, int dir) {
        if (dir == 0) {
            return new int[]{start[0], start[1] + 1}; 
        } else if (dir == 1) {
            return new int[]{start[0] - 1, start[1]}; 
        } else if (dir == 2) {
            return new int[]{start[0], start[1] - 1}; 
        } else {
            return new int[]{start[0] + 1, start[1]}; 
        }
    }

    //returns the squared distance (because there's no point in sqrt-ing it when everythign is relative anyways)
    private int calcDist (int[] p1, int[] p2) {
        return (int) (Math.pow((p1[0] - p2[0]), 2) + Math.pow((p1[1] - p2[1]), 2)); 
    }
}

class Snake {
    private static String[] d = new String[]{"right", "up", "left", "down"}; 

    //front of arraylist is the head, back of arraylist is the tail
    private ArrayList<int[]> pos = new ArrayList<>(); 
    //direction, 0 = right, 1 = up, 2 = left, 3 = down
    private int dir = 0; 

    public Snake (int[][] sPos) {
        for (int i = 0; i < sPos.length; i++) {
            pos.add(new int[]{sPos[i][0], sPos[i][1]}); 
        }
    }

    public void print () {
        for (int i = 0; i < pos.size(); i++) {
            System.out.printf("%d, %d\n", pos.get(i)[0], pos.get(i)[1]); 
        }
    }

    public ArrayList<int[]> getPos () {
        return pos; 
    }

    public int[] getHead () {
        return pos.get(0); 
    }

    public void changeDir (String direction) {
        direction = direction.toLowerCase(); 
        for (int i = 0; i < d.length; i++) {
            if (direction.equals(d[i])) {
                dir = i; 
                return; 
            }
        }
    }

    public void changeDir (int direction) {
        dir = direction; 
    }

    public int getDir () {
        return dir; 
    }
    
    //moves snake to the next state, and returns true if it eats the apple
    public boolean next (int[] applePos) {
        int[] h = new int[]{pos.get(0)[0], pos.get(0)[1]}; 
        //checks which direction it needs to go, and updates 
        if (dir%2 == 0) {
            //goes right or left (modifies col)
            h[1] -= 1; 
            if (dir == 0) {
                h[1] += 2; 
            }
        } else {
            //goes up or down (modifies row)
            h[0] -= 1; 
            if (dir == 3) {
                h[0] += 2; 
            }
        }
        pos.add(0, h); 

        if (h[0] != applePos[0] || h[1] != applePos[1]) {
            pos.remove(pos.size() - 1); 
            return false; 
        }
        return true; 
    }

    //returns true if pos contains the input array
    public boolean contains (int[] compare) {
        for (int[] p : pos) {
            if (p[0] == compare[0] && p[1] == compare[1]) {
                return true; 
            }
        }
        return false; 
    }
}

class Main {
    public static void main (String[] args) {
        //15 (rows) by 17 (cols)
        Board snakeBoard = new Board(15, 17); 
        

        //simulate! 
        LocalTime now = LocalTime.now(); 
        LocalTime update = LocalTime.now().plusNanos(500000000); 
        while (true) {
            now = LocalTime.now(); 
            if (now.isAfter(update)) {
                snakeBoard.next(); 
                snakeBoard.print(); 
                update = LocalTime.now().plusNanos(500000000); 
                if (snakeBoard.gameEnded()) {
                    break; 
                }
            }
        }
    }
}
