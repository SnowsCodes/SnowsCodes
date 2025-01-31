import java.util.*; 

class Player {
    //static variables
    static private int numPlayers = 0; 
    static private boolean hasPlayable = false; 
    
    //instance variables
    private ArrayList<String> cards = new ArrayList<String>(); 
    private String name; 
    
    //constructors
    public Player (String[] initialCards, String name) {
        cards = new ArrayList<>(Array.asList(initialCards)); 
        numPlayers++; 
        this.name = name; 
    }
    
    public Player (String[] initialCards) {
        cards = Array.asList(initialCards); 
        numPlayers++; 
        name = "Player " + numPlayers; 
    }
    
    //methods
    //add cards methods
    public void add (String s) {
        cards.add(s); 
    }
    
    public void add (String[] str) {
        for (String s : str) {
            cards.add(s); 
        }
    }
    
    //remove cards method
    public void remove (int id) {
        cards.remove(id); 
    }
    
    //find cards that can be played, given card on top of pile
    //WORK HERE
    public int[] findMatch (String c) {
        
    }
    
    //toString method
    public void toString () {
        System.out.print("[" + cards.get(0));
        for (int i = 1; i < cards.size(); i++) {
            System.out.print(", " + cards.get(i));
        }
        System.out.println("]");
    }
}

class Uno {
    //static variables
    private static ArrayList<Player> players; 
    
    //instance variables
    private ArrayList<String> cardList = new ArrayList<String>(Arrays.asList("Wild", "Wild", "Wild", "Wild", "Wild Draw Four", "Wild Draw Four", "Wild Draw Four", "Wild Draw Four", "Red 0", "Red 1", "Red 1", "Red 2", "Red 2", "Red 3", "Red 3", "Red 4", "Red 4", "Red 5", "Red 5", "Red 6", "Red 6", "Red 7", "Red 7", "Red 8", "Red 8", "Red 9", "Red 9", "Red Skip", "Red Skip", "Red Draw Two", "Red Draw Two", "Red Reverse", "Red Reverse", "Yellow 0", "Yellow 1", "Yellow 1", "Yellow 2", "Yellow 2", "Yellow 3", "Yellow 3", "Yellow 4", "Yellow 4", "Yellow 5", "Yellow 5", "Yellow 6", "Yellow 6", "Yellow 7", "Yellow 7", "Yellow 8", "Yellow 8", "Yellow 9", "Yellow 9", "Yellow Skip", "Yellow Skip", "Yellow Draw Two", "Yellow Draw Two", "Yellow Reverse", "Yellow Reverse", "Green 0", "Green 1", "Green 1", "Green 2", "Green 2", "Green 3", "Green 3", "Green 4", "Green 4", "Green 5", "Green 5", "Green 6", "Green 6", "Green 7", "Green 7", "Green 8", "Green 8", "Green 9", "Green 9", "Green Skip", "Green Skip", "Green Draw Two", "Green Draw Two", "Green Reverse", "Green Reverse", "Blue 0", "Blue 1", "Blue 1", "Blue 2", "Blue 2", "Blue 3", "Blue 3", "Blue 4", "Blue 4", "Blue 5", "Blue 5", "Blue 6", "Blue 6", "Blue 7", "Blue 7", "Blue 8", "Blue 8", "Blue 9", "Blue 9", "Blue Skip", "Blue Skip", "Blue Draw Two", "Blue Draw Two", "Blue Reverse", "Blue Reverse"));
}

class Main {
    public static void main(String[] args) {
        System.out.println("Try programiz.pro");
    }
}
