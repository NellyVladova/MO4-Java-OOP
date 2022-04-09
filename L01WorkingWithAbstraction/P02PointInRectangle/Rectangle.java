package L01WorkingWithAbstraction.P02PointInRectangle;

public class Rectangle {
    private Point bottomLeft;
    private Point topRight;

    public Rectangle(Point bottomLeft, Point topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    public boolean contains(Point point) {
        boolean left = bottomLeft.getX() <= point.getX() && bottomLeft.getY() <= point.getY();
        boolean right = topRight.getX() >= point.getX() && topRight.getY() >= point.getY();

        return left && right;
    }
}

