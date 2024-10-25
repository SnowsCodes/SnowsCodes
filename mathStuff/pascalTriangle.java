public class Main {
  public static void main (String[] args) {
    int rows = 10; 
    for (int i = 0; i <= rows; i++) {
      for (int j = ; ; ) {
        
      }
      
      for (int j = 0; j <= i; j++) {
        System.out.print(" " + combination(i, j));
      }
      System.out.println(); 
    }
  }
  
  public static int factorial(int num){
    int out = 1;
    for (int i = num; i > 1; i--) {
      out *= i; 
    }
    return out; 
  }
  
  public static int combination (int n, int r) {
    int out = 1; 
    for (int i = n; i >= r+1; i--) {
      out *= i;
    }
    return (out/factorial(n-r)); 
  }
}

