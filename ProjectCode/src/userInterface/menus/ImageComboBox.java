package userInterface.menus;

import java.awt.*;
import javax.swing.*;

public class ImageComboBox extends JFrame
{
    JComboBox comboBox;

    public ImageComboBox()
    {
        Object[] items =
        {
            new ImageIcon("about16.gif"),
            new ImageIcon("add16.gif"),
            new ImageIcon("copy16.gif")
        };
        comboBox = new JComboBox( items );
        getContentPane().add( comboBox, BorderLayout.NORTH );
    }

    public static void main(String[] args)
    {
        JFrame frame = new ImageComboBox();
        frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        frame.pack();
        frame.setLocationRelativeTo( null );
        frame.setVisible( true );
    }
}