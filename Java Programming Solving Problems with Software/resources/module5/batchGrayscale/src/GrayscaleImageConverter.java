import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class GrayscaleImageConverter {
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File file : dr.selectedFiles()) {
            ImageResource inFile = new ImageResource(file);
            ImageResource convertedFile = makeGray(inFile);
            convertedFile.setFileName("gray-"+inFile.getFileName());
            convertedFile.save();
            System.out.println("New "+convertedFile.getFileName()+" created.");
        }
    }
    public ImageResource makeGray(ImageResource inImage) {

        //Create new image with original image dimensions.
        ImageResource outImage = new ImageResource(inImage);

        //For each pixel, convert the original do gray scale version.
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            int avg = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;

            pixel.setRed(avg);
            pixel.setGreen(avg);
            pixel.setBlue(avg);
        }
        return outImage;
    }

    public void testSelectAndConvert() {
        selectAndConvert();
    }

    public static void main(String[] args) {
        GrayscaleImageConverter imgConv = new GrayscaleImageConverter();
        imgConv.testSelectAndConvert();
    }
}