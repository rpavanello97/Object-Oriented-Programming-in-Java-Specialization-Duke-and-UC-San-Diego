import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class InvertedImagesConverter {
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File file : dr.selectedFiles()) {
            ImageResource inFile = new ImageResource(file);
            ImageResource convertedFile = makeInversion(inFile);
            convertedFile.setFileName("inverted-"+inFile.getFileName());
            convertedFile.save();
            System.out.println("New "+convertedFile.getFileName()+" created.");
        }
    }
    public ImageResource makeInversion (ImageResource inImage) {

        //Create new image with original image dimensions.
        ImageResource outImage = new ImageResource(inImage);

        //For each pixel, convert the original to inverted version.
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());

            //Inverted color is 255 less actual value.
            pixel.setRed(255 - inPixel.getRed());
            pixel.setGreen(255 - inPixel.getGreen());
            pixel.setBlue( 255 - inPixel.getBlue());
        }
        return outImage;
    }

    public void testSelectAndConvert() {
        selectAndConvert();
    }

    public static void main(String[] args) {
        InvertedImagesConverter imgConv = new InvertedImagesConverter();
        imgConv.testSelectAndConvert();
    }
}
