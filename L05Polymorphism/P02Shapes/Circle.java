package L05Polymorphism.P02Shapes;

public class Circle extends Shape{
    private Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    public final Double getRadius() {
        return this.radius;
    }

    @Override
    public Double calculatePerimeter() {
        return Math.PI * getRadius() * getRadius();
    }

    @Override
    public Double calculateArea() {
        return 2 * Math.PI * this.radius;
    }
}
