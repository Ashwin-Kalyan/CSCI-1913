// @author: Ashwin Kalyan

public class Greyscale extends Transformation {
    /**
     * Creates a new Greyscale transformation. The image will be transformed to greyscale.
     * @param image - Image to be transformed
     * @param originalColor - Original color of the pixel
     * @param x - x coordinate of the pixel
     * @param y - y coordinate of the pixel
     * @return - Color of the greyscaled pixels after transformation
     */
    @Override
    protected RGBColor do_transform(int x, int y, RGBColor originalColor, RGBImage image) {
        int red = originalColor.getRed();
        int green = originalColor.getGreen();
        int blue = originalColor.getBlue();
        int grey = (red + green + blue) / 3;
        return new RGBColor(grey, grey, grey);
    }
}
