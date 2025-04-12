// @author: Ashwin Kalyan

public class Brighten extends Transformation {
    private int amount;

    /**
     * Creates a new Brighten transformation. The image will be brightened by the amount specified.
     * @param amount - Amount to brighten the image. Positive values will brighten the image, negative values will darken the image.
     */
    public Brighten(int amount) {
        this.amount = amount;
    }

    /**
     * Brightens the image by the amount specified. Positive values will brighten the image, negative values will darken the image.
     * @param x - x coordinate of the pixel
     * @param y - y coordinate of the pixel
     * @param originalColor - Original color of the pixel
     * @param image - Image to be transformed
     * @return - Color of the pixel after transformation
     */
    @Override
    protected RGBColor do_transform(int x, int y, RGBColor originalColor, RGBImage image) {
        int red = min(originalColor.getRed() + amount);
        int green = min(originalColor.getGreen() + amount);
        int blue = min(originalColor.getBlue() + amount);
        return new RGBColor(red, green, blue);
    }

    /**
     * Ensures that the value is between 0 and 255. If the value is greater than 255, it will return 255. If the value is less than 0, it will return 0.
     * @param value - Value to be checked
     * @return - Value between 0 and 255
     */
    private int min(int value) {
        if (value > 255) return 255;
        if (value < 0) return 0;
        return value;
    }
}

