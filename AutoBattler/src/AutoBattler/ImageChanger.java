package AutoBattler;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;

// https://www.tutorialspoint.com/how-to-set-modify-the-pixels-rgb-values-of-an-image-using-java-opencv-library

public class ImageChanger
{
    public BufferedImage makeRed(String original) throws IOException {
        BufferedImage img = ImageIO.read(this.getClass().getResource(original));
        for (int y = 0; y < img.getHeight(); y++)
        {
            for (int x = 0; x < img.getWidth(); x++) // iterate through each pixel
            {
                // contents of given pixel
                int pixel = img.getRGB(x,y);

                // Color object from pixel value
                Color color = new Color(pixel, true);

                // retrieve RGB values of pixel & modify to create mostly red image
                int red = (int)(color.getRed()/1.5);
                int green = color.getGreen()/7;
                int blue = color.getBlue()/7;

                // new color object
                color = new Color(red, green, blue);

                //set new color object to image
                img.setRGB(x, y, color.getRGB());
            }
        }
        return img;

    }

    public BufferedImage invertColours(String original) throws IOException {
        BufferedImage img = ImageIO.read(this.getClass().getResource(original));
        for (int y = 0; y < img.getHeight(); y++)
        {
            for (int x = 0; x < img.getWidth(); x++) // iterate through each pixel
            {
                // contents of given pixel
                int pixel = img.getRGB(x,y);

                // Color object from pixel value
                Color color = new Color(pixel, true);

                // retrieve RGB values of pixel & modify to create mostly red image
                int red = 255 - color.getRed();
                int green = 255- color.getGreen();
                int blue = 255 - color.getBlue();

                // new color object
                color = new Color(red, green, blue);

                //set new color object to image
                img.setRGB(x, y, color.getRGB());
            }
        }
        return img;

    }

}
