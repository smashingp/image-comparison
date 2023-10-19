package es.alop;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageUtils {

    public static BufferedImage Threshold(BufferedImage img, int requiredThresholdValue) {
        int height = img.getHeight();
        int width = img.getWidth();
        BufferedImage finalThresholdImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB) ;

        int red = 0;
        int green = 0;
        int blue = 0;

        for (int x = 0; x < width; x++) {
            try {

                for (int y = 0; y < height; y++) {
                    int color = img.getRGB(x, y);

                    red = (color & 0x00ff0000)  >> 16;
                    green = (color & 0x0000ff00)  >> 8;
                    blue = (color & 0x000000ff)  >> 0;

                    if((red+green+blue)/3 < (int) (requiredThresholdValue)) {
                        finalThresholdImage.setRGB(x,y,mixColor(0, 0,0));
                    }
                    else {
                        finalThresholdImage.setRGB(x,y,mixColor(255, 255,255));
                    }

                }
            } catch (Exception e) {
                e.getMessage();
            }
        }

        return finalThresholdImage;
    }

    private static int mixColor(int red, int green, int blue) {
        return red<<16|green<<8|blue;
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    public static Double compare(BufferedImage imgA, BufferedImage imgB) {

        // Assigning dimensions to image
        int width1 = imgA.getWidth();
        int width2 = imgB.getWidth();
        int height1 = imgA.getHeight();
        int height2 = imgB.getHeight();

        Double percentage = 0d;

        // Checking whether the images are of same size or
        // not
        if ((width1 != width2) || (height1 != height2))

            // Display message straightaway
            System.out.println("Error: Images dimensions"
                    + " mismatch");
        else {

            // By now, images are of same size

            long difference = 0;

            // treating images likely 2D matrix

            // Outer loop for rows(height)
            for (int y = 0; y < height1; y++) {

                // Inner loop for columns(width)
                for (int x = 0; x < width1; x++) {

                    int rgbA = imgA.getRGB(x, y);
                    int rgbB = imgB.getRGB(x, y);
                    int redA = (rgbA >> 16) & 0xff;
                    int greenA = (rgbA >> 8) & 0xff;
                    int blueA = (rgbA)&0xff;
                    int redB = (rgbB >> 16) & 0xff;
                    int greenB = (rgbB >> 8) & 0xff;
                    int blueB = (rgbB)&0xff;

                    difference += Math.abs(redA - redB);
                    difference += Math.abs(greenA - greenB);
                    difference += Math.abs(blueA - blueB);
                }
            }

            // Total number of red pixels = width * height
            // Total number of blue pixels = width * height
            // Total number of green pixels = width * height
            // So total number of pixels = width * height *
            // 3
            double total_pixels = width1 * height1 * 3;

            // Normalizing the value of different pixels
            // for accuracy

            // Note: Average pixels per color component
            double avg_different_pixels = difference / total_pixels;

            // There are 255 values of pixels in total
            percentage = (avg_different_pixels / 255) * 100;

            // Lastly print the difference percentage
            System.out.println("Difference Percentage-->" + percentage);
        }
        return null;
    }

}
