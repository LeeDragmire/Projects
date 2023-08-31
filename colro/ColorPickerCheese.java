package colro;

import java.awt.datatransfer.StringSelection;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorPickerCheese extends JFrame  {
    private static int r = 107;
    private static int g = 164;
    private static int b = 184;
            
    private JLabel redText = new JLabel("Red: " + r);
    private JLabel greenText = new JLabel("Green: " + g);
    private JLabel blueText = new JLabel("Blue: " + b);

    private JButton copyButton = new JButton("Copy RGB Values");
    private JButton returnVal = new JButton("Return Value");

    private boolean moved = false;

    private static boolean isAlive = true;

    private ColorCheese parentFrame;

    private JFrame getFrame() {
        return this;
    }

    private void popupRGB() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(r + ", " + g + ", " + b);
        clipboard.setContents(stringSelection, null);
        JOptionPane.showMessageDialog(this, "RGB Color Copied to Clipboard");
    
    }

    public static boolean isAlive() {
        return isAlive;
    }

    public int[] getRGBVals() {
        return new int[]{r, g, b};
    }

    public ColorPickerCheese(ColorCheese parent) {
        parentFrame = parent;

        this.setSize(466, 588);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.getContentPane().setBackground(new java.awt.Color(r, g, b));
        this.setLayout(null);
        // setDefaultLookAndFeelDecorated(true);


        JSlider rSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 107);
        JSlider gSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 164);
        JSlider bSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 184);


        rSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                int red = (int)source.getValue();
                r = red;
                getFrame().getContentPane().setBackground(new java.awt.Color(r, g, b));
                redText.setText("Red: " + r);
            }
        });
        rSlider.setBounds(150, 10, 300, 20);
        rSlider.setMajorTickSpacing(100);
        rSlider.setMinorTickSpacing(20);
        rSlider.setPaintTicks(true);


        gSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                int green = (int)source.getValue();
                g = green;
                getFrame().getContentPane().setBackground(new java.awt.Color(r, g, b));
                greenText.setText("Green: " + g);
            }
        });
        gSlider.setBounds(150, 30, 300, 20);
        gSlider.setMajorTickSpacing(100);
        gSlider.setMinorTickSpacing(20);
        gSlider.setPaintTicks(true);


        bSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                int blue = (int)source.getValue();
                b = blue;
                getFrame().getContentPane().setBackground(new java.awt.Color(r, g, b));
                blueText.setText("Blue: " + b);
            }
        });
        bSlider.setBounds(150, 50, 300, 20);
        bSlider.setMajorTickSpacing(100);
        bSlider.setMinorTickSpacing(20);
        bSlider.setPaintTicks(true);

        redText.setBounds(0, 10, 200, 25);
        redText.setOpaque(true);
        redText.setBackground(new java.awt.Color(0, 0, 0));
        redText.setForeground(new java.awt.Color(255, 255, 255));

        greenText.setBounds(0, 30, 200, 25);
        greenText.setOpaque(true);
        greenText.setBackground(new java.awt.Color(0, 0, 0));
        greenText.setForeground(new java.awt.Color(255, 255, 255));

        blueText.setBounds(0, 50, 200, 20);
        blueText.setOpaque(true);
        blueText.setBackground(new java.awt.Color(0, 0, 0));
        blueText.setForeground(new java.awt.Color(255, 255, 255));

        copyButton.setBounds(0, 70, 150, 25);
        copyButton.setOpaque(true);
        copyButton.setBackground(new java.awt.Color(0, 0, 0));
        copyButton.setForeground(new java.awt.Color(255, 255, 255));
        copyButton.addActionListener(e -> {
            popupRGB();
        });

        returnVal.setBounds(0,95, 150, 25);
        returnVal.setOpaque(true);
        returnVal.setBackground(new java.awt.Color(0, 0, 0));
        returnVal.setForeground(new java.awt.Color(255, 255, 255));
        returnVal.addActionListener(e -> {
            parentFrame.setRGBVals(r, g, b);
            this.dispose();
        });


        // addMouseListener(new MouseAdapter() {
        //     public void mouseReleased(MouseEvent e) {
        //         if (moved) new ColorPickerCheese();
        //         moved = false;
        //     }
            
        // });

        // addComponentListener(new ComponentAdapter() {
        //     public void componentMoved(ComponentEvent e) {
        //         moved = true;
                
        //     }
        // });

        


        add(rSlider);
        add(gSlider);
        add(bSlider);
        add(redText);
        add(greenText);
        add(blueText);
        add(copyButton);
        add(returnVal);

        // ColorCheese.waitForThread();
    }

    public static void main(String[] args) {
    }
}
