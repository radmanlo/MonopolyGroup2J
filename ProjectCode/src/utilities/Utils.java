package utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Utils {

    /**
     *
     * @param WIDTH new width of the image
     * @param HEIGHT new height of the image
     * @param path path of the image to be scaled
     * @return scaled image
     */
    public static BufferedImage scaleImage(int WIDTH, int HEIGHT, String path) {
        BufferedImage img = null;
        try {
            ImageIcon ii = new ImageIcon(path);//path to image
            img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = (Graphics2D) img.createGraphics();
            graphics.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
            graphics.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return img;
    }

    public static Font getFont() {
        Font font =  null;
        try {
            //Returned font is of pt size 1
            font = Font.createFont(Font.TRUETYPE_FONT, new File("langer.ttf"));

        } catch (IOException |FontFormatException e) {
            System.out.println("Failed to load font");
            e.printStackTrace();
        }
        return font;
    }
}
