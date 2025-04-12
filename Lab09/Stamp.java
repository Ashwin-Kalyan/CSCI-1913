public class Stamp extends Transformation {
    private final RGBImage stampImage;

    /**
     * Creates a new Stamp transformation. The stamp will be applied to the image.
     * @param stampImage - Image to be used as a stamp. The stamp will be applied over the image.
     */
    public Stamp(RGBImage stampImage) {
        this.stampImage = stampImage;
    }

    /**
     * Applies the stamp to the image. The stamp will be applied over the image.
     * @param x - x coordinate of the pixel
     * @param y - y coordinate of the pixel
     * @param originalColor - Original color of the pixel
     * @param image - Image to be transformed
     * @return - Color of the pixel after transformation, or if it doesn't satisfy the condition, the original color of the pixel
     */
    @Override
    protected RGBColor do_transform(int x, int y, RGBColor originalColor, RGBImage image) {
        // Check if current pixel is within stamp area
        if (x < stampImage.getWidth() && y < stampImage.getHeight()) {
            // Get stamp color at this position
            RGBColor stampColor = stampImage.getColor(x, y);
            // Blend colors by averaging each channel
            int red = (originalColor.getRed() + stampColor.getRed()) / 2;
            int green = (originalColor.getGreen() + stampColor.getGreen()) / 2;
            int blue = (originalColor.getBlue() + stampColor.getBlue()) / 2;
            return new RGBColor(red, green, blue);
        }
        // Outside stamp area, so return original color
        return originalColor;
    }
}
