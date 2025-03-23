// @author: Ashwin Kalyan

public class ShapeUtils {
    public static double distance(Point p1, Point p2) {
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static Point getCenter(Point[] points) {
        if (points.length == 0) {
            return new Point(0, 0);
        }

        double x = 0;
        double y = 0;

        for (Point p : points) {
            x += p.getX();
            y += p.getY();
        }

        return new Point(x / points.length, y / points.length);
    }

    public static double getArea(Circle c) {
        return Math.PI * Math.pow(c.getRadius(), 2);
    }

    public static double getArea(Ring r) {
        return (Math.PI * (Math.pow(r.getInnerCircle().getRadius() + r.getThickness(), 2)) - r.getInnerCircle().getArea());
    }

    public static boolean isIn(Circle c, Point p) {
        return distance(c.getCenter(), p) <= c.getRadius();
    }

}
