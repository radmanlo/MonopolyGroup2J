package userInterface.components;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    // Stroke size. 1 for better view
    protected int strokeSize = 1;
    protected Color shadowColor = new Color(0f, 0f, 0f, 0.3f);
    // enables shadow
    protected boolean shady = false;
    protected boolean highQuality = true;
    // horizontal and vertical arcs
    protected Dimension arcs = new Dimension(20, 20);
    // gap between panel and shadow
    protected int shadowGap = 5;
    protected int shadowOffset = 4;
    protected int shadowAlpha = 150;

    public RoundedPanel() {
        super();
        setOpaque(false);
    }

    public RoundedPanel(Dimension arcs) {
        this.arcs = arcs;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int shadowGap = this.shadowGap;
        Color shadowColorA = new Color(shadowColor.getRed(),
                shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
        Graphics2D graphics = (Graphics2D) g;

        if (highQuality) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

        //Draws shadow borders if any.
        if (shady) {
            graphics.setColor(shadowColorA);
            graphics.fillRoundRect(
                    shadowOffset,// X position
                    shadowOffset,// Y position
                    width - strokeSize - shadowOffset, // width
                    height - strokeSize - shadowOffset, // height
                    arcs.width, arcs.height);// arc Dimension
        } else {
            shadowGap = 1;
        }

        //Draws the rounded opaque panel with borders.
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - shadowGap,
                height - shadowGap, arcs.width, arcs.height);
        graphics.setColor(getForeground());
        graphics.setStroke(new BasicStroke(strokeSize));
//        graphics.drawRoundRect(0, 0, width - shadowGap,
//                height - shadowGap, arcs.width, arcs.height);

        graphics.setStroke(new BasicStroke());
    }
}