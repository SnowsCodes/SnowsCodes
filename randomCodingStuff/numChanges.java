import java.util.*; 

class Main {
    public static int numChanges (String from, String to) {
        if (from.equals(to)) {
            return 0; 
        } else if (from.isBlank() || to.isBlank()) {
            return 1; 
        }
        
        int out = -1; 
        List<String> both = new ArrayList<String>(); 
        List<String> fHas = new ArrayList<String>(); 
        List<String> tHas = new ArrayList<String>(); 
        
        
        int f = 0, t = 0, type = 0; 
        //type == 0 -> both strings have
        //type == 1 -> from has, to doesn't
        //type == 2 -> to has, from doesn't
        String temp = ""; 
        while (f < from.length() && t < to.length()) {
            if (type == 0) {
                if (from.charAt(f) == to.charAt(t)) {
                    temp += from.charAt(f); 
                    f++; 
                    t++; 
                } else {
                    System.out.println("HELP!!! at f = " + f + " and t = " + t);
                    f++; 
                    t++; 
                }
            } else {
                System.out.println("HELP!!! at f = " + f + " and t = " + t);
                f++; 
                t++; 
            }
        }
        
        
        return out; 
    }
    
    public static void main(String[] args) {
        String f = "awgadsfaweg"; 
        String t = "ewdsfwega"; 
        System.out.println(numChanges(f, t));
    }
}
