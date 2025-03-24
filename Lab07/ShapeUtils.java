// @author: Ashwin Kalyan

public class ShapeUtils {
    /**
     * Takes two points and computes the distance between them.
     * @param p1 - the first point, of object Point
     * @param p2 - the second point, of object Point
     * @return - the distance between the two points using the distance formula
     */
    public static double distance(Point p1, Point p2) {
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
    /**
     * Takes an array of points and returns a point that represents the center of the points.
     * @param points - an array of Point objects
     * @return - a Point object that represents the center of the given points
     */
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
    /**
     * Computes the area of a Circle object using pi*r^2
     * @param c - a Circle object
     * @return - the area of the Circle object
     */
    public static double getArea(Circle c) {
        return Math.PI * Math.pow(c.getRadius(), 2);
    }
    /**
     * Computes the area of a Ring object using the formula for the area of a circle
     * @param r - a Ring object
     * @return - the area of the Ring object
     */
    public static double getArea(Ring r) {
        return (Math.PI * (Math.pow(r.getInnerCircle().getRadius() + r.getThickness(), 2)) - r.getInnerCircle().getArea());
    }
    /**
     * Determines if a point is inside a Circle object
     * @param c - a Circle object
     * @param p - a Point object
     * @return - true if the point is inside the circle, false otherwise
     */
    public static boolean isIn(Circle c, Point p) {
        return distance(c.getCenter(), p) <= c.getRadius();
    }

}
