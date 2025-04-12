// @author: Ashwin Kalyan

public class TransformationUtils {
    /**
     * Applies a transformation to an image and saves the result to a file.
     * @param transformations - Array of transformations to be applied to the image
     * @param inputFile - Path to the input image file
     * @param outputFile - Path to the output image file
     */
    public static void transformMany(Transformation[] transformations, String inputFile, String outputFile) {
        RGBImage image = RGBImageUtil.load(inputFile);

        for (Transformation transformation : transformations) {
            if (transformation != null) {
                image = transformation.transform(image);
            }
        }

        RGBImageUtil.saveImage(image, outputFile);
    }
}
