import java.util.*;

/**
 * Fibonacci - a class implementing various methods used to produce the nth Fibonacci number
 * @author Colin Heffernan
 */
public class Fibonacci{
  
  /**
   * fibI - iterative method of producing the number
   */
  public static int fibI(int N){
    int first = 0;
    int second = 1;
    int current = first + second;
    for(int i = 0; i < N - 1; i++){
      current = first + second;
      first = second;
      second = current;
    }
    return current;
  }
  
  /**
   * fibR - Recursive method of producing the number
   */
  public static int fibR(int N){
    if (N == 0){
      return 0;
    }
    else if (N == 1){
      return 1;
    }
    else{
      return fibR(N - 1) + fibR(N - 2);
    }
  }
  
  public static void main(String [] args){
    Scanner input = new Scanner(System.in);
    int index;
    try{
      System.out.println("Please enter the index of the desired fibonacci number from 1-40 or enter a non-positive integer to quit: ");
      index = input.nextInt();
      while(index > 0 && index < 41){
        System.out.println("Generating Fibonacci number for index: " + index);
        System.out.println("Iterative Method: " + fibI(index));
        System.out.println("Recursive Method: " + fibR(index));
        System.out.println("Please enter the index of the desired fibonacci number from 1-40 or enter a non-positive integer to quit: ");
        index = input.nextInt();
      }
    }
    catch(Exception e){
      System.out.println("Invalid input");
    }
  }
}
