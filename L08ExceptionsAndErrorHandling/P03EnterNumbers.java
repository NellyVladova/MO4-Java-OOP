package L08ExceptionsAndErrorHandling;

import java.util.Scanner;

public class P03EnterNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //???????????????
        while (true){
            try {
                int start = Integer.parseInt(scanner.nextLine());
                int end = Integer.parseInt(scanner.nextLine());

                readNumber(start, end);
            }catch (NumberFormatException exception){
                System.out.println();
            }
        }

    }
    private static void readNumber(int start, int end){
        for (int i = start; i <= end; i++) {
            System.out.println(i);
        }
    }
}
