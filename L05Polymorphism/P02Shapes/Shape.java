package L05Polymorphism.P02Shapes;

public abstract class Shape {
    private Double perimeter;
    private Double area;

    public Double getPerimeter() {
        return this.perimeter;
    }

    public Double getArea() {
        return this.area;
    }

    public abstract Double calculatePerimeter();
    public abstract Double calculateArea();
}
