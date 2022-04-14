package L05Polymorphism.P02Shapes;

public class Rectangle extends Shape{
    private Double height;
    private Double width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    public Double getHeight() {
        return this.height;
    }

    public Double getWidth() {
        return this.width;
    }

    @Override
    public Double calculatePerimeter() {
        return 2 * (getHeight() + getWidth());
    }

    @Override
    public Double calculateArea() {
        return this.height * this.width;
    }
}
