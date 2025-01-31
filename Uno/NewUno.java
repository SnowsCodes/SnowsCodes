class Player {
    //static variables
    static private int numPlayers = 0; 
    static private boolean hasPlayable = false; 
    
    //instance variables
    private ArrayList<String> cards = new ArrayList<String>(); 
    private String name; 
    
    //constructors
    public Player (String[] initialCards, String name) {
        cards = Array.asList(initialCards); 
        numPlayers++; 
        this.name = name; 
    }
    
    public Player (String[] initialCards) {
        cards = Array.asList(initialCards); 
        numPlayers++; 
        name = "Player " + numPlayers; 
    }
}



class Main {
    public static void main(String[] args) {
        System.out.println("Try programiz.pro");
    }
}
