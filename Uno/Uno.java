class Player {
    //instance variables
    //place is what place they are in (1st place, 2nd, etc), and if place = 0, that means that they haven't used all their cards yet
    private int place = 0;
    //list of the cards the player has
    private String[] cards; 
    //name of player
    private String name = ""; 
    
    //constructor
    public Player (String[] initialCards, String name) {
        this.name = name; 
        cards = initialCards; 
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
    
    public void listCards() {
        String out = "[ " + cards[0]; 
        for (int i = 1; i < cards.length; i++) {
            out += ", " + cards[i]; 
        }
        out += " ]";
        System.out.println(out); 
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
            //WORKIGN HERE, for some reason when i input Red 0, red 3, red 1, and red skip arent added???
            System.out.println (cards[i] + "  " + c);
            System.out.println(cards[i].substring(0, 1) + "   " + c.substring(0, 1));
            System.out.println(cards[i].substring(0, 1).equals(c.substring(0, 1)));
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
            pList[i] = new Player(temp, "Player " + i); 
        }
        
        //put down the first card
        int id = (int) (Math.random() * cardList.length); 
        String c = cardList[id]; 
        //p o e
        while (c.substring(0, 1).equals("W") || c.substring(c.length()-1).equals("p") || c.substring(c.length()-1).equals("o") || c.substring(c.length()-1).equals("e")) {
            id = (int) (Math.random() * cardList.length);
            c = cardList[id]; 
        }
        
        discard = new String[]{c}; 
        removeCard(id); 
    }
    
    //returns a card from cardList and removes it from the list 
    public String drawCard () {
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
    
    public addDiscard (String c) {
        
    }
    
    //ok actually play the game here
    public void nextTurn () {
        int[] temp = pList[pTurn].findMatch(lastCard); 
        if (temp.length == 0) {
            pList[pTurn].addCard(drawCard);
            temp = pList[pTurn].findMatch(lastCard); 
            if (temp.length > 0) {
                System.out.println(pList[pTurn].getName() + " drew a " + pList[pTurn].getLastCard() + " and played it");
                //ADD TO DISCARD PILE
            }
        }
        
        pTurn++; 
    }
    
}



class Main {
    
    
    public static void main(String[] args) {
        
        
        /*
        Uno game = new Uno(); 
        Player p1 = game.pList[0];
        int[] a = p1.findMatch("Red 0"); 
        System.out.print("[");
        if (a.length > 0) {
            for (int i = 0; i < a.length-1; i++) {
                System.out.print(a[i] + ", ");
            }
            System.out.println(a[a.length-1] + "]"); 
        } else {
            System.out.println("]");
        }
        p1.listCards(); 
        /*String[] temp = {"a", "b", "c"}; 
        Player p1 = new Player(temp);
        p1.listCards(); 
        p1.addCard("d"); 
        p1.listCards(); 
        temp[0] = "f"; 
        p1.addCard(temp);
        p1.listCards(); 
        p1.removeCard(3); 
        p1.listCards(); */
    }
}
