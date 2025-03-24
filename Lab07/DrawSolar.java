// @author: Ashwin Kalyan

import java.awt.Color;

public class DrawSolar {
    public static void main(String[] args) {
        ShapeDrawer canvas = new ShapeDrawer(500, 500);

        Ring moonOrbit = new Ring(new Circle(new Point(250, 100), 40), 1);
        canvas.setStroke(Color.WHITE); 
        canvas.draw(moonOrbit);

        Ring earthOrbit = new Ring(new Circle(new Point(250, 250), 150), 1);
        canvas.setStroke(Color.WHITE);
        canvas.draw(earthOrbit);

        // Place the moon on the circumference of the moon's orbit
        Circle moon = new Circle(new Point(250 + 40, 100), 11);
        canvas.setStroke(Color.GRAY);
        canvas.setFill(Color.LIGHT_GRAY);
        canvas.draw(moon);

        // Create the sun at the center of the canvas with a radius of 50
        Circle sun = new Circle(new Point(250, 250), 50);
        canvas.setStroke(Color.YELLOW);
        canvas.setFill(Color.ORANGE);
        canvas.draw(sun);
      
        // Place the earth on the circumference of the earth's orbit
        Circle earth = new Circle(new Point(250, 100), 20);
        canvas.setStroke(Color.BLUE);
        canvas.setFill(Color.CYAN);
        canvas.draw(earth);

        canvas.writeToFile("solar.png");    
    }
}
