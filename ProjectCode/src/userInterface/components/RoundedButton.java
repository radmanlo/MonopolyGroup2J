package userInterface.components;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class RoundedButton extends Component {

    private ActionListener actionListener;     // Post action events to listeners
    private String label; // Button's label
    private boolean pressed = false; // For changing color when btn is pressed
    private boolean hovered = false;
    private int radius = 70;
    private int fontSize = 20;
    private Color color = new Color(230, 112, 112);

    /**
     * Constructs a RoundedButton with no label.
     */
    public RoundedButton() {
    }

    public RoundedButton(String label) {
        super();
        this.label = label;
        setProps();
    }

    /**
     * Constructs a RoundedButton with the specified label.
     *
     * @param label the label of the button
     */
    public RoundedButton(String label, int fontSize, int radius, Color color) {
        this.label = label;
        this.fontSize = fontSize;
        this.radius = radius;
        this.color = color;
        setProps();
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
    }

    private void setProps() {
        setBackground(this.color);
        Font bTahoma = new Font("Tahoma", Font.BOLD, this.fontSize);
        setFont(bTahoma);
    }

    /**
     * gets the label
     *
     * @see setLabel
     */
    public String getLabel() {
        return label;
    }

    /**
     * sets the label
     *
     * @see getLabel
     */
    public void setLabel(String label) {
        this.label = label;
        invalidate();
        repaint();
    }

    /**
     * paints the RoundedButton
     */
    @Override
    public void paint(Graphics g) {

        // paint the interior of the button
        if (pressed) {
            g.setColor(getBackground().darker().darker());
        } else if (hovered) {
            g.setColor(getBackground().brighter());
        }
        else {
            g.setColor(getBackground());
        }

        // Draw background
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, this.radius, this.radius);

        // draw the border of the button
        g.setColor(new Color(1f,0f,0f,.0f )); // transparent border
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, this.radius, this.radius);

        // draw the label at center
        Font f = getFont();
        if (f != null) {
            FontMetrics fm = getFontMetrics(getFont());
            g.setColor(getForeground());
            g.drawString(label, getWidth() / 2 - fm.stringWidth(label) / 2, getHeight() / 2 + fm.getMaxDescent());
        }
    }

    /**
     * The preferred size of the button.
     */
    @Override
    public Dimension getPreferredSize() {
        Font f = getFont();
        if (f != null) {
            FontMetrics fm = getFontMetrics(getFont());
            int max = Math.max(fm.stringWidth(label) + 40, fm.getHeight() + 40);
            return new Dimension(max, max);
        } else {
            return new Dimension(100, 100);
        }
    }

    /**
     * The minimum size of the button.
     */
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(100, 100);
    }

    /**
     * Adds the specified action listener to receive action events from this
     * button.
     *
     * @param listener the action listener
     */
    public void addActionListener(ActionListener listener) {
        actionListener = AWTEventMulticaster.add(actionListener, listener);
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
    }

    /**
     * Removes the specified action listener so it no longer receives action
     * events from this button.
     *
     * @param listener the action listener
     */
    public void removeActionListener(ActionListener listener) {
        actionListener = AWTEventMulticaster.remove(actionListener, listener);
    }

    /**
     * Determine if click was inside round button.
     */
    @Override
    public boolean contains(int x, int y) {
        Shape shape = null;
        // If the button has changed size, make a new shape object.
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Rectangle(0, 0, getWidth()+10, getHeight()+10);
        }
        return shape.contains(x, y);
    }

    /**
     * Paints the button and distribute an action event to all listeners.
     */
    @Override
    public void processMouseEvent(MouseEvent e) {
        Graphics g;
        switch (e.getID()) {
            case MouseEvent.MOUSE_PRESSED:
                pressed = true;
                repaint();
                break;
            case MouseEvent.MOUSE_RELEASED:
                if (actionListener != null) {
                    actionListener.actionPerformed(new ActionEvent(
                            this, ActionEvent.ACTION_PERFORMED, label));
                }
                if (pressed == true) {
                    pressed = false;

                    repaint();
                }
                break;
            case MouseEvent.MOUSE_ENTERED:

                break;
            case MouseEvent.MOUSE_EXITED:
//                hovered = false;
                if (pressed) {
                    pressed = false;
                    repaint();
                }
                break;
        }
        super.processMouseEvent(e);
    }
}