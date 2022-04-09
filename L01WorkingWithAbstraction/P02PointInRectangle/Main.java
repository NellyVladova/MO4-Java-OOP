package L01WorkingWithAbstraction.P02PointInRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] input = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int bottomLeftX = input[0];
        int bottomLeftY = input[1];
        Point bottomLeft = new Point(bottomLeftX, bottomLeftY);
        int topRightX = input[2];
        int topRightY = input[3];
        Point topRight = new Point(topRightX, topRightY);
        Rectangle rectangle = new Rectangle(bottomLeft, topRight);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            int[] coordinates = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            Point point = new Point(coordinates[0], coordinates[1]);
            System.out.println(rectangle.contains(point));
        }
    }
}

