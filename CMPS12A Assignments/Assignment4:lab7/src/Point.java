class Point {
    private double x, y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public double getDistance(Point point) {
        return Math.sqrt(Math.pow(x - point.getX(), 2) + Math.pow(y - point.getY(), 2));
    }
}

abstract class Shape {
    public abstract double getArea();
    public abstract double getPerimeter();
}

interface Symmetric {
    Point getPointOfSymmetry();
}

class Triangle extends Shape {
    private Point firstPoint, secondPoint, thirdPoint;
    public Triangle(Point firstPoint, Point secondPoint, Point thirdPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        this.thirdPoint = thirdPoint;
    }

    @Override
    public double getArea() {
            double s = getPerimeter()/2;
            double a = firstPoint.getDistance(secondPoint);
            double b = secondPoint.getDistance(thirdPoint);
            double c = thirdPoint.getDistance(firstPoint);
            double area = Math.sqrt(s * (s-a) * (s-b) * (s-c));
            return area;
        }

    @Override
    public double getPerimeter() {
        double distanceOne = firstPoint.getDistance(secondPoint);
        double distanceTwo = thirdPoint.getDistance(firstPoint);
        double distanceThree = secondPoint.getDistance(thirdPoint);
        return (distanceOne + distanceTwo + distanceThree);
    }

    public Point getFirstPoint() { return this.firstPoint; }
    public Point getSecondPoint() { return this.secondPoint; }
    public Point getThirdPoint() { return this.thirdPoint; }
}

class Rectangle extends Shape {
    private Point topLeftPoint;
    private double length, width;

    public Rectangle(Point topLeftPoint, double length, double width) {
        this.topLeftPoint = topLeftPoint;
        this.length = length;
        this.width = width;
    }

    @Override
    public double getArea() {
        return (length * width);
    }
     @Override
     public double getPerimeter() {
        return 2 * (length + width);
     }
    public Point getTopLeftPoint() { return this.topLeftPoint; }
    public double getLength() { return this.length; }
    public double getWidth() { return this.width; }

}

class Trapezoid extends Shape {
    private Point topLeftPoint, bottomLeftPoint;
    private double topSide, bottomSide;

    public Trapezoid(Point topLeftPoint, Point bottomLeftPoint, double topSide, double bottomSide) {
        this.topLeftPoint = topLeftPoint;
        this.bottomLeftPoint = bottomLeftPoint;
        this.topSide = topSide;
        this.bottomSide = bottomSide;
    }

    @Override
    public double getArea() {
        double height = topLeftPoint.getY() - bottomLeftPoint.getY();
        return ((topSide + bottomSide) / 2) * height;
    }

    @Override
    public double getPerimeter() {
        Point topRight = new Point(topLeftPoint.getX() + topSide, topLeftPoint.getY());
        Point bottomRight = new Point(bottomLeftPoint.getX() + bottomSide, bottomLeftPoint.getY());
        double first = topLeftPoint.getDistance(bottomLeftPoint);
        double second = topRight.getDistance(bottomRight);

        return (first + second + topSide + bottomSide);
    }

    public Point getTopLeftPoint() { return topLeftPoint; }
    public Point getBottomLeftPoint() { return bottomLeftPoint; }
    public double getTopSide() { return topSide; }
    public double getBottomSide() { return bottomSide; }
}

class Circle extends Shape implements Symmetric {
    private Point center;
    private double radius;
    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return (Math.PI * radius * radius);
    }

    @Override
    public double getPerimeter() {
        return (Math.PI * 2 * radius);
    }

    @Override
    public Point getPointOfSymmetry() {
        return center;
    }

    public Point getCenter() { return center; }
    public double getRadius() { return radius; }
}

class EquilateralTriangle extends Triangle implements Symmetric {
    private Point topPoint;
    private double side;

    public EquilateralTriangle(Point topPoint, double side) {
        super(topPoint, new Point(topPoint.getX()-side / 2, (topPoint.getY() - Math.sqrt(((side*side) - (side/2)*(side/2))))), new Point(topPoint.getX()+side/2, (topPoint.getY()-Math.sqrt(((side*side) - (side/2)*(side/2))))));
        this.side = side;
        this.topPoint = topPoint;
    }

    public Point getTopPoint() {
        return topPoint;
    }

    public double getSide() {
        return side;
    }
    @Override
    public Point getPointOfSymmetry() {
        double y = topPoint.getY() - (side * Math.sqrt(3) / 3);
        return new Point(topPoint.getX(), y);
    }

    @Override
    public double getArea() {
        return (Math.sqrt(3) / 4) * side * side;
    }

    @Override
    public double getPerimeter() {
        return 3 * side;
    }

    @Override
    public Point getFirstPoint() {
        return topPoint;
    }

    @Override
    public Point getSecondPoint() {
        return new Point(topPoint.getX() - side / 2, (topPoint.getY() - Math.sqrt(((side*side) - (side/2)*(side/2)))));
    }

    @Override
    public Point getThirdPoint() {
        return new Point(topPoint.getX() + side/2, (topPoint.getY()- Math.sqrt(((side*side) - (side/2)*(side/2)))));
    }
}

class Square extends Rectangle implements Symmetric {
    private Point topLeftPoint;
    private double side;
    public Square(Point topLeftPoint, double side) {
        super(topLeftPoint, side, side);
        this.topLeftPoint = topLeftPoint;
        this.side = side;
    }

    @Override
    public Point getPointOfSymmetry() {
        double xValue = this.getTopLeftPoint().getX()+(this.getSide()/2);
        double yValue = this.getTopLeftPoint().getY() - (this.getSide()/2);
        return new Point(xValue, yValue);
    }

    public double getSide() { return side; }
}

