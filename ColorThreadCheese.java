package colro;

import javax.swing.JFrame;

public class ColorThreadCheese extends Thread {
    private ColorCheese parentFrame;
    
    public ColorThreadCheese(ColorCheese parent) {
        parentFrame = parent;
    }

    public void run() {
        ColorPickerCheese test = new ColorPickerCheese(parentFrame);
    }
}