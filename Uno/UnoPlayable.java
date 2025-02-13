//its a WIP
class Player {
    //static variable
    static private int playerCount = 0; 
    
    //instance variables
    //list of the cards the player has
    private String[] cards; 
    //name of player
    private String name = ""; 
    
    //constructor
    public Player (String[] initialCards, String name) {
        this.name = name; 
        cards = initialCards; 
        playerCount++; 
    }
    
    public void addCard (String input) {
        String[] temp = new String[cards.length+1]; 
        for (int i = 0; i < cards.length; i++) {
            temp[i] = cards[i]; 
        }
        temp[cards.length] = input; 
        cards = temp; 
    }
    
    public void addCard (String[] input) {
        int numAdded = input.length;
        String[] temp = new String[cards.length + numAdded]; 
        for (int i = 0; i < cards.length; i++) {
            temp[i] = cards[i]; 
        }
        for (int i = 0; i < numAdded; i++) {
            temp[cards.length + i] = input[i];
        }
        cards = temp; 
    }
    
    public void removeCard (int index) {
        String[] temp = new String[cards.length-1]; 
        for (int i = 0; i < cards.length-1; i++) {
            temp[i] = cards[i]; 
        }
        if (index != cards.length-1) {
            temp[index] = cards[cards.length-1];
        }
        cards = temp; 
    }
    
    public int[] findMatch (String c) {
        int[] out = new int[0]; 
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].substring(0, 1).equals("W") || cards[i].substring(0, 1).equals(c.substring(0, 1)) || cards[i].substring(cards[i].length()-1).equals(c.substring(c.length()-1))) {
                int[] temp = new int[out.length+1];
                for (int j = 0; j < out.length; j++) {
                    temp[j] = out[j]; 
                }
                temp[temp.length-1] = i; 
                out = temp; 
            }
        }
        return out; 
    }
    
    public String getLastCard () {
        return cards[cards.length-1]; 
    }
    
    public String getName () {
        return name; 
    }
    
    public int getNumCards () {
        return cards.length; 
    }
    
    public String getCard (int id) {
        return cards[id]; 
    }
    
    public static int getPlayerCount () {
        return playerCount; 
    }
}


class Uno {
    //variables
    //uno has 108 cards, 4 wild, 4 +4 cards, and 25 of each color
    //for each color, there are one zero card, two of each of the 1-9 cards, 2 skips, 2 reverse, and 2 draw twos
    private String[] cardList = {
        "Wild", "Wild", "Wild", "Wild", "Wild Draw Four", "Wild Draw Four", "Wild Draw Four", "Wild Draw Four", "Red 0", "Red 1", "Red 1", "Red 2", "Red 2", "Red 3", "Red 3", "Red 4", "Red 4", "Red 5", "Red 5", "Red 6", "Red 6", "Red 7", "Red 7", "Red 8", "Red 8", "Red 9", "Red 9", "Red Skip", "Red Skip", "Red Draw Two", "Red Draw Two", "Red Reverse", "Red Reverse", "Yellow 0", "Yellow 1", "Yellow 1", "Yellow 2", "Yellow 2", "Yellow 3", "Yellow 3", "Yellow 4", "Yellow 4", "Yellow 5", "Yellow 5", "Yellow 6", "Yellow 6", "Yellow 7", "Yellow 7", "Yellow 8", "Yellow 8", "Yellow 9", "Yellow 9", "Yellow Skip", "Yellow Skip", "Yellow Draw Two", "Yellow Draw Two", "Yellow Reverse", "Yellow Reverse", "Green 0", "Green 1", "Green 1", "Green 2", "Green 2", "Green 3", "Green 3", "Green 4", "Green 4", "Green 5", "Green 5", "Green 6", "Green 6", "Green 7", "Green 7", "Green 8", "Green 8", "Green 9", "Green 9", "Green Skip", "Green Skip", "Green Draw Two", "Green Draw Two", "Green Reverse", "Green Reverse", "Blue 0", "Blue 1", "Blue 1", "Blue 2", "Blue 2", "Blue 3", "Blue 3", "Blue 4", "Blue 4", "Blue 5", "Blue 5", "Blue 6", "Blue 6", "Blue 7", "Blue 7", "Blue 8", "Blue 8", "Blue 9", "Blue 9", "Blue Skip", "Blue Skip", "Blue Draw Two", "Blue Draw Two", "Blue Reverse", "Blue Reverse"
        
    }; 
    
    //create the player variables in an array list, by default there are 4 players
    public Player[] pList = new Player[4]; 
    //determines which player's turn it is
    private int pTurn = 0; 
    //determines if it will go clockwise or counterclockwise
    private boolean isClockwise = true; 
    // the last card that a player has played
    private String lastCard = ""; 
    
    private String[] discard; 
    private String winner = ""; 
    
    //constructor
    public Uno () {
        //create a player for each spot in the player list
        for (int i = 0; i < pList.length; i++) {
            String[] temp = new String[7];
            for (int j = 0; j < 7; j++) {
                int picked = (int) (Math.random() * cardList.length); 
                temp[j] = cardList[picked];
                removeCard(picked); 
            }
            pList[i] = new Player(temp, "Player " + (i+1)); 
        }
        
        //put down the first card
        int id = (int) (Math.random() * cardList.length); 
        String c = cardList[id]; 
        while (c.substring(0, 1).equals("W") || c.substring(c.length()-1).equals("p") || c.substring(c.length()-1).equals("o") || c.substring(c.length()-1).equals("e")) {
            id = (int) (Math.random() * cardList.length);
            c = cardList[id]; 
        }
        
        lastCard = c; 
        System.out.println("There are " + Player.getPlayerCount() + " players");
        System.out.println("The starting card is " + c);
        discard = new String[]{c}; 
        removeCard(id); 
    }
    
    //constructor that have a varying number of players
    public Uno (int numPlayers) {
        pList = new Player[numPlayers];
        //create a player for each spot in the player list
        for (int i = 0; i < pList.length; i++) {
            String[] temp = new String[7];
            for (int j = 0; j < 7; j++) {
                int picked = (int) (Math.random() * cardList.length); 
                temp[j] = cardList[picked];
                removeCard(picked); 
            }
            pList[i] = new Player(temp, "Player " + (i+1)); 
        }
        
        //put down the first card
        int id = (int) (Math.random() * cardList.length); 
        String c = cardList[id]; 
        while (c.substring(0, 1).equals("W") || c.substring(c.length()-1).equals("p") || c.substring(c.length()-1).equals("o") || c.substring(c.length()-1).equals("e")) {
            id = (int) (Math.random() * cardList.length);
            c = cardList[id]; 
        }
        
        lastCard = c; 
        System.out.println("There are " + Player.getPlayerCount() + " players");
        System.out.println("The starting card is " + c);
        discard = new String[]{c}; 
        removeCard(id); 
    }
    
    //returns a card from cardList and removes it from the list 
    public String drawCard () {
        if (cardList.length == 0) {
            cardList = discard; 
            discard = new String[0]; 
        }
        int id = (int) (Math.random() * cardList.length); 
        String c = cardList[id]; 
        removeCard(id); 
        return c; 
    }
    
    //removes a card from cardList
    public void removeCard (int index) {
        String[] temp = new String[cardList.length-1];
        for (int i = 0; i < cardList.length-1; i++) {
            temp[i] = cardList[i];
        }
        if (index != cardList.length-1) {
            temp[index] = cardList[cardList.length-1];
        }
        cardList = temp; 
    }
    
    
    public void addDiscard (String c) {
        String[] temp = new String[discard.length+1]; 
        for (int i = 0; i < discard.length; i++) {
            temp[i] = discard[i]; 
        }
        temp[temp.length-1] = c; 
        discard = temp; 
    }
    
    private void incPTurn () {
        if (isClockwise == true) {
            pTurn++; 
        } else {
            pTurn--; 
        }
        if (pTurn < 0) {
            pTurn += pList.length; 
        }
        pTurn %= pList.length; 
    }
    
    //ok actually play the game here
    public void nextTurn () {
        String name = pList[pTurn].getName(); 
        int[] temp = pList[pTurn].findMatch(lastCard); 
        boolean cardPlayed = false; 
        
        if (temp.length == 0) {
            //if there are no cards that can be played, draw a card
            pList[pTurn].addCard(drawCard());
            System.out.print(name + " drew a card");
            //if the card that got drawn can be played, play card
            temp = pList[pTurn].findMatch(lastCard);
            if (temp.length > 0) {
                System.out.println(" and played " + pList[pTurn].getLastCard());
                //add to discard pile, set it as lastCard(the variable that tracks what is the last card played), and remove card from player
                addDiscard(pList[pTurn].getLastCard());
                lastCard = pList[pTurn].getLastCard();
                pList[pTurn].removeCard(pList[pTurn].getNumCards()-1);
                
                //set cardPlayed as true
                cardPlayed = true; 
            }
        } else {
            cardPlayed = true; 
            //choose a random card to play 
            int id = (int) (Math.random() * temp.length);
            addDiscard(pList[pTurn].getCard(temp[id]));
            lastCard = pList[pTurn].getCard(temp[id]); 
            pList[pTurn].removeCard(temp[id]); 
        }
        
        if (cardPlayed == true) {
            char lastChar = lastCard.charAt(lastCard.length()-1);
            System.out.print(name + " played " + lastCard);
            if (lastChar == 'p') {
                //if the card played is a skip, skip the next player's turn
                incPTurn(); 
            } else if (lastChar == 'e') {
                //if the card played is a reverse, change the direction
                isClockwise = !isClockwise;
            } else if (lastChar == 'd' || lastChar == 'r') {
                //if the card played is a wild card or a wild +4, choose a random color 
                int randomVal = (int) (Math.random() * 4);
                if (randomVal == 0) {
                    lastCard = "Red "; 
                } else if (randomVal == 1) {
                    lastCard = "Yellow "; 
                } else if (randomVal == 2) {
                    lastCard = "Green "; 
                } else if (randomVal == 3) {
                    lastCard = "Blue "; 
                }
                System.out.print(" and changed the color to " + lastCard);
                
                //if the card is a wild +4, the next player draws four cards and skip their turn 
                if (lastChar == 'r') {
                    incPTurn();  
                    System.out.print("\n" + name + " drew four cards");
                    String[] difTemp = new String[4]; 
                    for (int i = 0; i < 4; i++) {
                        difTemp[i] = drawCard(); 
                    }
                    pList[pTurn].addCard(difTemp); 
                }
            } else if (lastChar == 'o') {
                //if the card played is a draw two, the next player draw two cards and skip their turn
                incPTurn(); 
                System.out.print("\n" + pList[pTurn].getName() + " drew two cards");
                String[] difTemp = new String[2]; 
                difTemp[0] = drawCard(); 
                difTemp[1] = drawCard(); 
                pList[pTurn].addCard(difTemp); 
            }
            
            int cardsLeft = pList[pTurn].getNumCards();
            if (cardsLeft == 1) {
                System.out.print("\n" + name + " shouted \"UNO!\"");
            } else if (cardsLeft == 0) {
                System.out.print("\n" + name + " won!");
                winner = name; 
            }
        } 
        
        System.out.println(); 
        incPTurn(); 
    }
    
    public String getWinner () {
        return winner; 
    }
}

class Main {
    public static void main(String[] args) {
        Uno game = new Uno((int) (Math.random() * 5) + 4); 
        String winnerName = ""; 
        
        while (winnerName.equals("")) {
            game.nextTurn(); 
            winnerName = game.getWinner(); 
        }
    }
}
