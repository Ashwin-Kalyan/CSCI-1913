// @author: Ashwin Kalyan

public class AddBorder extends Transformation {
    private int width;
    private RGBColor borderColor;

    /**
     * Creates a new AddBorder transformation. The border will be added to the
     * image. The border will be added over the image and not change the size of the image.
     * @param width - Pixel width of the border
     * @param borderColor - Color of border
     */
    public AddBorder(int width, RGBColor borderColor) {
        this.width = width;
        this.borderColor = borderColor;
    }

    /**
     * Adds a border to the image. The border will be added over the image and not change the size of the image.
     * @param x - x coordinate of the pixel
     * @param y - y coordinate of the pixel
     * @param originalColor - Original color of the pixel
     * @param image - Image to be transformed
     * @return - Color of the pixel after transformation, or if it doesn't satisfy the condition, the original color of the pixel
     */
    @Override
    protected RGBColor do_transform(int x, int y, RGBColor originalColor, RGBImage image) {
        if (x < width || x >= image.getWidth() - width || y < width || y >= image.getHeight() - width) {
            return borderColor;
        } 
        return originalColor;
    }
}
