/*
* 
* This project is unfinished. I just want to save my attempt of the snake game using BFS
* It runs out of memory (because its BFS), sometimes have index out of bound errors? and also sometimes it doesn't choose the best path
* I'm going to use a* to do the snake game now
* 
*/

import java.util.LinkedList; 
import java.time.LocalTime;  
import java.util.Iterator;
import java.util.ArrayDeque; 
import java.util.ArrayList; 


class Board {
    // static variables
    // sParts is snake parts
    // 0 = empty
    // 1-6 = body
    // 7-10 = head
    // 11 = apple
    private static String[] sParts = new String[]{". ", "─ ", "│ ", "┌ ", "┐ ", "└ ", "┘ ", "V ", "< ", "Λ ", "> ", "🍎"}; 

    // instance variables
    private int numRow; 
    private int numCol; 
    private int[][] gameBoard; 
    private Snake currentSnake; 
    private int[] apple; 
    private boolean gameEnd = false; 

    // for pathfinding
    // private LinkedList<int[]> path; 
    private ArrayDeque<Integer> pDirs; 

    // constructor
    public Board(int r, int c) {
        // define the dimensions of the board
        numRow = r; 
        numCol = c; 

        // fill gameBoard with zeroes
        gameBoard = new int[r][c]; 

        // find the startin gpositions of the snake and the apple
        int xPos = (int) Math.ceil(c / 4.0); 
        int yPos = r / 2; 
        currentSnake = new Snake(new int[][]{{yPos, xPos}, {yPos, xPos-1}, {yPos, xPos-2}, {yPos, xPos-3}}); 
        apple = new int[]{yPos, (c - xPos)}; 

        // find starting path (always just goes right)
        pDirs = new ArrayDeque<Integer>(); 
        for (int i = 0; i < (c - xPos - xPos); i++) {
            pDirs.add(0); 
        }
    }

    // update the values of gameBoard using the position of currentSnake and apple
    // always gets called at the end of next
    private void updateBoard() { 
        // reset values of gameBoard
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                gameBoard[i][j] = 0; 
            }
        }

        LinkedList<int[]> pos = currentSnake.getPos(); 

        for (int i = 0; i < pos.size(); i++) {
            int[] point = pos.get(i); 
            // have placeholder values for now
            int[] prev = point; 
            int[] next = point; 
            // define prev and next based on whether they exist
            if (i != 0) {
                prev = pos.get(i-1); 
            } 
            if (i != pos.size() - 1) {
                next = pos.get(i+1); 
            }

            // val is the index for sParts
            int val; 
            
            if (i == 0) {
                // case: head
                val = (point[0] > next[0])? 7 : 
                      (point[1] < next[1])? 8 :
                      (point[0] < next[0])? 9 : 10; 
            } else if (i == pos.size() - 1) {
                // case: the last segment of the tail
                val = (point[0] == prev[0])? 1 : 2; 
            } else {
                // case: middle parts of the snake
                val = (prev[0] == next[0])? 1 :
                      (prev[1] == next[1])? 2 : 
                      ((prev[0] < point[0] || next[0] < point[0]) && (prev[1] < point[1] || next[1] < point[1]))? 6 : // connects up and left
                      (prev[0] < point[0] || next[0] < point[0])? 5 : // connects up (other one must be left)
                      (prev[1] < point[1] || next[1] < point[1])? 4 : 3; // connects left (other one must be below), and else must connect below and right
            }

            gameBoard[point[0]][point[1]] = val; 
        }
        gameBoard[apple[0]][apple[1]] = 11; 
    }

    // visualizes the board
    public void print() {
        System.out.println("score: " + (currentSnake.size() - 4)); 

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                System.out.print(sParts[gameBoard[i][j]]); 
            }
            System.out.println(); 
        }
        System.out.println(); 
    }

    public void next() {
        //prints the current path
        Iterator<Integer> printPath = pDirs.clone().iterator(); 
        System.out.println("Path: "); 
        while (printPath.hasNext()) {
            System.out.print(printPath.next() + " "); 
        }
        System.out.println("\n"); 

        // check if method is called when the game is already over
        if (gameEnd) {
            System.out.println("The game had ended."); 
        }

        // change directions based on pathfinding
        if (pDirs.size() > 0) {
            currentSnake.changeDir(pDirs.getFirst()); 
            pDirs.removeFirst(); 
        }

        // moves the snake to the next state, and stores whether the apple got eaten
        boolean eatApple = currentSnake.next(apple); 
        if (eatApple) {
            System.out.println("The apple got eaten!"); 
        }

        // checks if the snake hits the wall
        int[] head = currentSnake.getHead(); 
        if (head[0] < 0 || head[0] >= numRow || head[1] < 0 || head[1] >= numCol) {
            gameEnd = true; 
            System.out.println("Uh oh! You hit a wall.");
            return;  
        }

        // checks if the snake hits itself
        Iterator<int[]> snakePos = currentSnake.getPos().iterator(); 
        snakePos.next(); // makes sure the while loop skips the first value
        while (snakePos.hasNext()) {
            int[] body = snakePos.next(); 
            
            // checks that it is not the last part of snake
            if (!snakePos.hasNext()) {
                break; 
            }

            // checks if snake hit itself
            if (body[0] == head[0] && body[1] == head[1]) {
                gameEnd = true; 
                System.out.println("Uh oh! You hit yourself."); 
                return; 
            }
        }

        // if the apple is eaten, randomly choose a new spot for the apple
        while (eatApple) {
            apple[0] = (int) (Math.random() * numRow); 
            apple[1] = (int) (Math.random() * numCol); 
            // check if the snake is on the apple. if not, break the while loop
            boolean appleNeedsReset = false; 
            // doesn't declare because its reusing the same variable name + type
            snakePos = currentSnake.getPos().iterator(); 
            while (snakePos.hasNext()) {
                int[] body = snakePos.next(); 
                if (apple[0] == body[0] && apple[1] == body[1]) {
                    appleNeedsReset = true; 
                    break; 
                }
            }
            if (!appleNeedsReset) {
                break; 
            }
        }

        // update board
        updateBoard(); 
        print(); 

        // pathfind and save in pDirs
        if (eatApple) {
            //System.out.println("YAY PATHFIND"); 
            findPath(currentSnake, apple); 
        }
    }

    public boolean gameEnded() {
        return gameEnd; 
    }

    // takes in linked list (snake pos) and apple pos
    // finds path using bfs to apple, dfs from apple to tail
    // directly modifies pDirs (acts as the return value)
    private void findPath(Snake needToBeClonedSnake, int[] applePos) {
        ArrayList<Snake> snakeBranches = new ArrayList<>(); 
        ArrayList<ArrayDeque<Integer>> dirBranches = new ArrayList<>(); 
        
        snakeBranches.add(needToBeClonedSnake.clone()); 
        dirBranches.add(new ArrayDeque<Integer>()); 

        // while loop to find the path T-T
        while (true) {
            //print path so ik how the calculation is going
            /*
            for (ArrayDeque<Integer> printPath : dirBranches) {
                ArrayDeque<Integer> putBack = new ArrayDeque<Integer>(); 
                Iterator<Integer> pathIter = printPath.iterator(); 
                
                while(pathIter.hasNext()) {
                    int nextThenPutBack = pathIter.next(); 
                    System.out.print(nextThenPutBack + " "); 
                    putBack.add(nextThenPutBack); 
                }
                System.out.println(); 
                printPath = putBack; 
            }*/

            ArrayList<Snake> nextSnakeBranches = new ArrayList<>(); 
            ArrayList<ArrayDeque<Integer>> nextDirBranches = new ArrayList<>(); 

            ArrayList<Snake> appleSnake = new ArrayList<>(); 
            ArrayList<ArrayDeque<Integer>> appleDir = new ArrayList<>(); 

            // goes through every branch in snakeBranches, branches the snakes, 
            // and adds them to nextSnakeBranches and/or appleSnake accordingly, and in the correct order
            int c = 0; // counter of index of the snake (too lazy to change the for each loop into a for loop)
            for (Snake branchedSnake : snakeBranches) {
                // for each snake in snakeBranches
                ArrayList<Snake> sBranches = new ArrayList<>(); 
                ArrayList<ArrayDeque<Integer>> dBranches = new ArrayList<>(); 

                // make a copy that goes left, straight, and right in that order
                int ogDir = branchedSnake.getDir(); 
                for (int i = -1; i < 2; i++) {
                    int tempDir = (ogDir + i + 4) % 4; // the actual direction its going
                    Snake temp = branchedSnake.clone(); 
                    ArrayDeque<Integer> tempDirs = dirBranches.get(c).clone(); 
                    
                    // advances the snake in specified direction
                    temp.changeDir(tempDir); 
                    boolean eatsApple = temp.next(applePos); 
                    tempDirs.add(tempDir); 
                    // if the copy eats the apple, add it to appleSnake & appleDir, otherwise add it to sBranches and dBranches
                    // note: if it eats an apple, it is guarentee not to crash into itself
                    if (eatsApple) {
                        appleSnake.add(temp); 
                        appleDir.add(tempDirs); 
                    } else {
                        sBranches.add(temp); 
                        dBranches.add(tempDirs); 
                    }
                }
                

                // check if any snakes died, if yes, ELIMINATE THEM
                for (int i = sBranches.size() - 1; i >= 0; i--) {
                    Snake temp = sBranches.get(i); 
                    int[] head = temp.getHead(); 
                    boolean isDead = false; 

                    // check for hitting wall
                    if (head[0] < 0 || head[1] < 0 || head[0] > numRow || head[1] > numCol) {
                        isDead = true; 
                    }

                    // check for hitting self
                    Iterator<int[]> it = temp.getPos().iterator(); 

                    int[] tempBody = it.next(); 
                    while (!isDead && it.hasNext()) { // this doesn't run if it hits the wall
                        tempBody = it.next(); 
                        if (tempBody[0] == head[0] && tempBody[1] == head[1]) {
                            isDead = true; 
                            break; 
                        }
                    }

                    if (isDead) {
                        sBranches.remove(i); 
                        dBranches.remove(i); 
                    }
                }

                // calculate how far each snake is to the apple
                int[] dist = new int[sBranches.size()]; 
                for (int i = 0; i < dist.length; i++) {
                    int[] snakeHead = sBranches.get(i).getHead(); 
                    // calc Manhattan distance
                    dist[i] = Math.abs(snakeHead[0] - applePos[0]) + Math.abs(snakeHead[1] - applePos[0]); 
                }

                // add the snakes to nextSnakeBranches, in the order of closest to least close
                // if there is a tie between the snakes and one of them is straight, add the straight one first
                // if there isn't a straight one, add the first one (it will go left)
                for (int i = 0; i < dist.length; i++) {
                    int closest = 0; 
                    for (int j = 1; j < dist.length; j++) {
                        if (dist[closest] > dist[j]) {
                            closest = j; 
                        } else if (dist[closest] == dist[j] && sBranches.get(j).getDir() == 0) {
                            closest = j; 
                        }
                    }
                    nextSnakeBranches.add(sBranches.get(closest)); 
                    nextDirBranches.add(dBranches.get(closest)); 
                    //change the dist for the oen that got added so it wont be added again
                    dist[closest] = numRow * numCol; 
                }


                // increase counter
                c++; 
            }
            
            //System.out.println("num that reaches apple: " + appleSnake.size()); 
            // of the snakes that reach the apple, remove the ones that do not have a path to its tail
            for (int i = appleSnake.size() - 1; i >= 0; i--) {
                // for each snake that reaches apple (aPos = apple snake's position)
                LinkedList<int[]> aPos = appleSnake.get(i).clone().getPos(); 
                ArrayList<Integer> aDirs = new ArrayList<>(); 
                aDirs.add(appleSnake.get(i).getDir()); 
                int[] target = aPos.getLast(); 
                int startingLen = aPos.size(); 
                boolean backtrack = false; 
                boolean reachesApple = false; 

                // check (using dfs) if there is a path to that snake's tail
                while (true) {
                    /*for (int j = 0; j < aDirs.size(); j++) {
                        System.out.print(aDirs.get(j) + " "); 
                    }
                    System.out.println(); */


                    int wrongDir = aDirs.get(aDirs.size() - 1); // only used when it's backtracking
                    if (backtrack) {
                        aPos.removeFirst(); 
                        aDirs.remove(aDirs.size() - 1); 
                    }
                    int[] head = aPos.getFirst(); 
                    int forward = aDirs.get(aDirs.size() - 1); 

                    int[][] aNext = new int[3][2]; 
                    int[] dirWent = new int[3]; 
                    int[] dist = new int[3]; 
                    // adds in the order of left, straight, right
                    // also stores the (Manhattan) distance to tail in dist
                    for (int j = 0; j < aNext.length; j++) {
                        int dir = (forward + (j-1) + 4) % 4; 
                        dirWent[j] = dir; 
                        aNext[j] = (dir == 0)? new int[]{head[0], (head[1] + 1)} : // right
                                   (dir == 1)? new int[]{(head[0] - 1), head[1]} : // up
                                   (dir == 2)? new int[]{head[0], (head[1] - 1)} : new int[]{(head[0] + 1), head[1]}; // left, down
                        dist[j] = Math.abs(aNext[j][0] - target[0]) + Math.abs(aNext[j][1] - target[1]); 
                    }

                    // check if any path reaches an apple, if one does, break the while loop
                    // and set reachesApple to true to go check if the next snake can reach the apple
                    for (int j = 0; j < dist.length; j++) {
                        if (dist[j] == 0) {
                            reachesApple = true; 
                            break; 
                        }
                    }
                    if (reachesApple) {
                        break; 
                    }

                    // for each snake, check if they hit themselves/the wall
                    // if yes, set their distance to -1 
                    for (int j = 0; j < aNext.length; j++) {
                        head = aNext[j]; // reusing head because it has served its purpose
                        // check if hit wall
                        if (head[0] < 0 || head[1] < 0 || head[0] >= numRow || head[1] >= numCol) {
                            dist[j] = -1; 
                            continue; 
                        }
                        // check if hit self
                        for (int k = 0; k < startingLen-1; k++) {
                            int[] bodyPart = aPos.get(k); 
                            if (bodyPart[0] == head[0] && bodyPart[1] == head[1]) {
                                dist[j] = -1; 
                                break; 
                            }
                        }
                    }

                    // order the snakes based on how close they are to the apple (exclude ones with -1 distance)
                    // value in order represents the index of the snake/dirWent/dist
                    ArrayList<Integer> order = new ArrayList<>(); 
                    for (int j = 0; j < dist.length; j++) {
                        if (dist[j] == -1) {
                            continue; 
                        }
                        boolean added = false; 
                        for (int k = 0; k < order.size(); k++) {
                            if (dist[j] < dist[order.get(k)]) {
                                order.add(k, j); 
                                added = true; 
                                break; 
                            }
                        }
                        if (!added) {
                            order.add(j); 
                        }
                    }

                    // backtrack if: 
                    // there's nothing in order OR
                    // the last element in order corresponds to the same direction as the wrong direction
                    int last = order.size()-1; 
                    if ((order.size() == 0) || (backtrack && dirWent[order.get(last)] == wrongDir)) {
                        backtrack = true; 
                        aPos.removeFirst(); 
                        aDirs.remove(aDirs.size() - 1); 
                    } 

                    // if there is no backtrack, determine which direction to go based on order
                    int orderIndex = -1; 
                    if (backtrack) {
                        for (int j = 0; j < order.size(); j++) {
                            if (dirWent[order.get(j)] == wrongDir) {
                                orderIndex = j; 
                                break; 
                            }
                        }
                    }
                    orderIndex += 1; 

                    // advance in the direction that orderIndex determines! 
                    aPos.addFirst(aNext[order.get(orderIndex)]); 
                    aDirs.add(dirWent[order.get(orderIndex)]); 

                    // set backtrack as false (because we're not backtracking)
                    backtrack = false; 
                }

                // if there isn't, remove that snake (and it's associated path) from the apple arraylists
                if (!reachesApple) {
                    appleSnake.remove(i); 
                    appleDir.remove(i); 
                }
            }
            
            
            // if the apple arraylists are empty, continue the while loop
            if (appleSnake.size() == 0) {
                snakeBranches = nextSnakeBranches; 
                dirBranches = nextDirBranches; 
                continue; 
            }

            // (because of the previous step, the following code only runs if apple arraylists aren't empty)
            // find the number of turns for each snake (and find the minimum number of turns)
            int[] numTurns = new int[appleSnake.size()]; 
            int min = numRow * numCol; //guarantees it'll be overwritten by the first snake
            for (int i = 0; i < appleDir.size(); i++) {
                int turn = 0; 
                Iterator<Integer> dirs = appleDir.get(i).iterator(); 
                //prev direction
                int pDir = dirs.next(); 
                while (dirs.hasNext()) {
                    int cDir = dirs.next(); 
                    if (pDir != cDir) {
                        turn++; 
                    }
                    pDir = cDir; 
                }
                numTurns[i] = turn; 
                if (turn < min) {
                    min = turn; 
                }
            }

            // select the first snake that has the minimum turns and set pDirs to the snake's corresponding path
            for (int i = 0; i < appleDir.size(); i++) {
                if (numTurns[i] == min) {
                    pDirs = appleDir.get(i); 
                    break; 
                }
            }
            
            // break from while loop! because the path has been found!!!!!!!
            break; 
        }
    }
}

class Snake {
    // front of linkedlist is head, back of linkedlist is tail
    private LinkedList<int[]> pos = new LinkedList<>(); 
    // direction, 0 = right, 1 = up, 2 = left, 3 = down
    private int dir = 0; 

    public Snake(int[][] sPos) {
        for (int i = 0; i < sPos.length; i++) {
            pos.add(sPos[i]); 
        }
    }

    // used for the clone method
    public Snake(LinkedList<int[]> sPos) {
        for (int[] bodyPos : sPos) {
            pos.add(bodyPos); 
        }
    }

    public void print() {
        Iterator<int[]> snakePos = pos.iterator(); 
        while (snakePos.hasNext()) {
            int[] snakePart = snakePos.next(); 
            System.out.println(snakePart[0] + ", " + snakePart[1]); 
        }
    }

    public LinkedList<int[]> getPos() {
        return pos; 
    }

    public int[] getHead() {
        return pos.getFirst(); 
    }

    public int size() {
        return pos.size(); 
    }

    public void changeDir(int direction) {
        dir = direction; 
    }

    public int getDir() {
        return dir; 
    }

    public Snake clone() {
        // create a copy (has same position)
        Snake out = new Snake(pos); 
        // change the direction to also match
        out.changeDir(dir); 
        return out; 
    }

    // advances snake in the direction of dir
    // returns whether or not it eats the apple
    public boolean next (int[] applePos) {
        int[] newHead = new int[]{pos.getFirst()[0], pos.getFirst()[1]}; 
        // checks which direction it needs to go and updates
        // if dir is an odd number, modify newHead accordingly
        newHead[0] += (dir == 1)? -1 :    // goes up
                      (dir == 3)? 1 : 0;  // if dir is 3, go up, otherwise do nothing
        newHead[1] += (dir == 0)? 1 :     // goes right
                      (dir == 2)? -1 : 0; // if dir is 2, go left, otherwise do nothing
        pos.addFirst(newHead); 

        // checks if snake eats apple, if not, remove the last element
        if (newHead[0] != applePos[0] || newHead[1] != applePos[1]) {
            pos.removeLast(); 
            return false; 
        }
        return true; 
    }
}

class Main {
    public static void main (String[] args) {
        // 15 (rows) by 17 (cols)
        Board snakeBoard = new Board(15, 17); 

        // simulate! 
        LocalTime now = LocalTime.now(); 
        // 20 frames/second
        //LocalTime update = LocalTime.now().plusNanos(50_000_000); 
        LocalTime update = LocalTime.now().plusNanos(100_000_000); 
        while (true) {
            now = LocalTime.now(); 
            if (now.isAfter(update)) {
                snakeBoard.next(); 
                //snakeBoard.print(); 
                update = LocalTime.now().plusNanos(50_000_000); 
                if (snakeBoard.gameEnded()) {
                    break; 
                }
            }
        }
    }
}
