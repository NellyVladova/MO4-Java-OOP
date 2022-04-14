package L05Polymorphism.P02Shapes;

public class Main {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(4, 5);
        Shape circle = new Circle(6.0);

        System.out.println(rectangle.calculatePerimeter());
        System.out.println(rectangle.calculateArea());
        System.out.println(circle.calculatePerimeter());
        System.out.println(circle.calculateArea());

    }
}
