package L08ExceptionsAndErrorHandling;

import java.util.Scanner;

public class P02SquareRoot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try{
            int number = Integer.parseInt(scanner.nextLine());
            System.out.printf("%.2f\n", Math.sqrt(number));
        }catch (NumberFormatException exception){
            System.out.println("Invalid");
        }
        finally{
            System.out.println("Goodbye");
        }
    }
}
