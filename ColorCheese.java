package colro;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import javax.swing.*;

public class ColorCheese extends JFrame {
    public static boolean start = true;

    private static final Object monitor = new Object();
    private static boolean monitorState = false;

    private JButton change = new JButton("Change Color");
    private JButton copyHex = new JButton("Copy");
    private JButton copyRGB = new JButton("Copy");
    private JButton copyCompHex = new JButton("Copy");
    private JButton copyCompRGB = new JButton("Copy");
    private JButton colorPick = new JButton("Pick Color");

    private JPanel paneRight = new JPanel();

    private JLabel normal = new JLabel("");
    private JLabel comp = new JLabel("");
    private JLabel hexNorm = new JLabel("");
    private JLabel hexComp = new JLabel("");

    public static int[] colorPickArray = new int[3];

    private int r = (int)(Math.random() *255)+1;
    private int g = (int)(Math.random() *255)+1;
    private int b = (int)(Math.random() *255)+1;

    public static void waitForThread() {
        monitorState = true;
        while (monitorState) {
          synchronized (monitor) {
            try {
              monitor.wait(); // wait until notified
            } catch (Exception e) {}
          }
        }
      }

    public static void unlockWaiter() {
        synchronized (monitor) {
            monitorState = false;
            monitor.notifyAll(); // unlock again
        }
    }

    private String switchCase(int input) {
        String output = "";
        switch(input / 16) {
            case 10:
                output += "A";
                break;
            case 11:
                output += "B";
                break;
            case 12:
                output += "C";
                break;
            case 13:
                output += "D";
                break;
            case 14:
                output += "E";
                break;
            case 15:
                output += "F";
                break;  
            default:
                output += input / 16;
                break;
        }
        switch(input % 16) {
            case 10:
                output += "A";
                break;
            case 11:
                output += "B";
                break;
            case 12:
                output += "C";
                break;
            case 13:
                output += "D";
                break;
            case 14:
                output += "E";
                break;
            case 15:
                output += "F";
                break;  
            default:
                output += input % 16;
                break;
        }
        return output;
    }

    private String getHex(int red, int green, int blue) {
        String output = "#";
        output += switchCase(red);
        output += switchCase(green);
        output += switchCase(blue);

        return output;
    }

    private void popupHex(int i) {
        if (i == 0) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection stringSelection = new StringSelection(getHex(r, g, b));
            clipboard.setContents(stringSelection, null);
            JOptionPane.showMessageDialog(this, "Hex Color Copied to Clipboard");
        } else {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection stringSelection = new StringSelection(getHex(Math.abs(r - 255), Math.abs(g - 255), Math.abs(b -255)));
            clipboard.setContents(stringSelection, null);
            JOptionPane.showMessageDialog(this, "Hex Color Copied to Clipboard");
        }
    }

    private void popupRGB(int i) {
        if (i == 0) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection stringSelection = new StringSelection(r + ", " + g + ", " + b);
            clipboard.setContents(stringSelection, null);
            JOptionPane.showMessageDialog(this, "RGB Color Copied to Clipboard");
        } else {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection stringSelection = new StringSelection(Math.abs(r - 255) + ", " + Math.abs(g - 255) + ", " + Math.abs(b -255));
            clipboard.setContents(stringSelection, null);
            JOptionPane.showMessageDialog(this, "RGB Color Copied to Clipboard");
        }
        
    }

    public int[] getRGBVals() {
        return new int[]{r, g, b};
    }

    public void setRGBVals(int red, int green, int blue) {
        r = red;
        g = green;
        b = blue;
        setColor();
    }

    public static void sleep(int i) throws InterruptedException {
        Thread.sleep(i);
    }

    private void setColor() { 
        this.getContentPane().setBackground(new java.awt.Color(r, g, b));
        normal.setText("RGB Values : " + r + ", " + g + ", " + b);
        normal.setForeground(new java.awt.Color(Math.abs(r - 255), Math.abs(g - 255), Math.abs(b -255)));
        hexNorm.setForeground(new java.awt.Color(Math.abs(r - 255), Math.abs(g - 255), Math.abs(b -255)));
        hexNorm.setText("Hex Code is " + getHex(r, g, b));

        paneRight.setBackground(new java.awt.Color(Math.abs(r - 255), Math.abs(g - 255), Math.abs(b -255)));

        comp.setForeground(new java.awt.Color(r, g, b));
        comp.setText("RGB Values : " + Math.abs(r-255) + ", " + Math.abs(g-255) + ", " + Math.abs(b-255));
        hexComp.setForeground(new java.awt.Color(r, g, b));
        hexComp.setText("Hex Code is " + getHex(Math.abs(r - 255), Math.abs(g - 255), Math.abs(b -255)));
    }

    private int setRPopUp() {
        String m = JOptionPane.showInputDialog(this, "Set red Value", 107);
        try {
            Integer.parseInt(m);
        } catch (NumberFormatException e) {
            return 107;
        }
        if (m == null || m.isEmpty() || Integer.parseInt(m) < 0 || Integer.parseInt(m) > 255) {
            return 107;
        }
        return Integer.parseInt(m);
    }

    private int setBPopUp() {
        String m = JOptionPane.showInputDialog(this, "Set blue Value", 164);
        try {
            Integer.parseInt(m);
        } catch (NumberFormatException e) {
            return 164;
        }
        if (m == null || m.isEmpty() || Integer.parseInt(m) < 0 || Integer.parseInt(m) > 255) {
            return 164;
        }
        return Integer.parseInt(m);
    }

    private int setGPopUp() {
        String m = JOptionPane.showInputDialog(this, "Set green Value", 184);
        try {
            Integer.parseInt(m);
        } catch (NumberFormatException e) {
            return 184;
        }
        if (m == null || m.isEmpty() || Integer.parseInt(m) < 0 || Integer.parseInt(m) > 255) {
            return 184;
        }
        return Integer.parseInt(m);
    }

    private void setRGB() {
        r = setRPopUp();
        g = setGPopUp();
        b = setBPopUp();
        setColor();
    }

    public ColorCheese() {
        this.setSize(466, 588);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);

        paneRight.setBounds(233, 0, 233, 588);
        paneRight.setLayout(null);
        
        normal.setBounds(50, 150, 200, 50);

        hexNorm.setBounds(50, 200, 200, 50);
        
        comp.setBounds(50, 150, 200, 50);

        hexComp.setBounds(50, 200, 200, 50);
        
        change.setBounds(190, 300, 125, 30);
        copyHex.setBounds(80, 240, 70, 20);
        copyRGB.setBounds(80, 190, 70, 20);
        copyCompHex.setBounds(80, 240, 70, 20);
        copyCompRGB.setBounds(80, 190, 70, 20);
        colorPick.setBounds(200, 330, 100, 30);

        setColor();

        paneRight.add(comp);
        paneRight.add(hexComp);
        paneRight.add(copyCompHex);
        paneRight.add(copyCompRGB);
        
        this.add(change);
        this.add(copyRGB);
        this.add(copyHex);
        this.add(colorPick);
        this.add(paneRight);
        this.add(normal);
        this.add(hexNorm);
    
        change.addActionListener(e -> {
            r = (int)(Math.random() *255)+1;
            g = (int)(Math.random() *255)+1;
            b = (int)(Math.random() *255)+1;

            setColor();
        });

        copyHex.addActionListener(e -> {
            popupHex(0);
        });

        copyRGB.addActionListener(e -> {
            popupRGB(0);
        });

        copyCompHex.addActionListener(e -> {
            popupHex(2);
        });

        copyCompRGB.addActionListener(e -> {
            popupRGB(2);
        });

        colorPick.addActionListener(e -> {
            // ColorPickerCheese test = new ColorPickerCheese(this);
            // ColorThreadCheese t = new ColorThreadCheese(this);
            // t.start();

            // try {
            //     sleep(30000);
            // } catch (InterruptedException e1) {
            //     System.out.println("funny err");
            // }
            setRGB();

            // setColor();
        });
    }

    public static void main(String[] args) {
        new ColorCheese();
    }
}