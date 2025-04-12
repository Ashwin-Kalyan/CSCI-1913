// @author: Ashwin Kalyan

public class ColorPallet extends Transformation {
    private final RGBColor[] pallet;

    /**
     * Creates a new ColorPallet transformation. The image will be transformed to the closest color in the pallet.
     * @param pallet - Array of RGBColor objects representing the color pallet
     */
    public ColorPallet(RGBColor[] pallet) {
        this.pallet = pallet;
    }

    /**
     * Transforms the image to the closest color in the pallet.
     * @param x - x coordinate of the pixel
     * @param y - y coordinate of the pixel
     * @param originalColor - Original color of the pixel
     * @param image - Image to be transformed
     * @return - Color of the closest colored pixels after transformation
     */
    @Override
    protected RGBColor do_transform(int x, int y, RGBColor originalColor, RGBImage image) {
        RGBColor closestColor = pallet[0];
        double minDistance = RGBColor.distance(originalColor, closestColor);

        for (int i = 1; i < pallet.length; i++) {
            double currentDistance = RGBColor.distance(originalColor, pallet[i]);
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                closestColor = pallet[i];
            }
        }
        return closestColor;
    }
}