package timer;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import javax.swing.*;


class TimeTest {

    private static long startTime = 0;
    private static boolean startReset = false;

    private static void fontSet(JLabel x) {
        x.setFont(new Font("Serif", Font.PLAIN, 50));
    }

    private static void fontSet(JSpinner x) {
        x.setFont(new Font("Serif", Font.PLAIN, 30));
    }

    private static void fontSet(JButton x) {
        x.setFont(new Font("Serif", Font.PLAIN, 25));
    }

    public static void timer(JLabel timeMin, JLabel timeSec, JSpinner spinSec, JSpinner spinMin, JFrame frame) throws InterruptedException{
        while (true) {
            if (startReset) {
                // Thread.sleep(1000);
                long elapsedTime = System.currentTimeMillis() - startTime;
                long elapsedSeconds = elapsedTime / 1000;
                long secondsDisplay = elapsedSeconds % 60;
                long elapsedMinutes = elapsedSeconds / 60;
    
                if ((Integer) spinSec.getValue() <= secondsDisplay) {
                    timeSec.setText(String.valueOf((Integer) spinSec.getValue() + 60 - secondsDisplay));
                    timeMin.setText(String.valueOf((Integer) spinMin.getValue() - elapsedMinutes - 1));
                    if (((Integer) spinMin.getValue() - elapsedMinutes) <= 0 && ((Integer) spinSec.getValue() - secondsDisplay) <= 0) {
                        startReset = false;
                    }
                    
                } else {
                    timeSec.setText(String.valueOf((Integer) spinSec.getValue() - secondsDisplay));
                    timeMin.setText(String.valueOf((Integer) spinMin.getValue() - elapsedMinutes));
                    if (((Integer) spinMin.getValue() - elapsedMinutes) <= 0 && ((Integer) spinSec.getValue() - secondsDisplay) <= 0) {
                        startReset = false;
                    }
                }
            } else { 
                if (Integer.parseInt(timeSec.getText()) != 0 && Integer.parseInt(timeMin.getText()) != 0) {
                    timeSec.setText("0");
                    timeMin.setText("0");
                    Thread.sleep(100);
                    frameShake(frame);
                }
                timeSec.setText("0");
                timeMin.setText("0");
            }
        } 
    }

    public static void frameShake(JFrame f) {
        Point currLocation = f.getLocationOnScreen();
        
        for (int i = 0; i < 1500; i++) {
            int xRand = (int) (Math.random() * 100) + 1;
            int yRand = (int) (Math.random() * 100) + 1;
            Point pos1 = new Point(currLocation.x + xRand, currLocation.y + yRand);
            Point pos2 = new Point(currLocation.x - xRand, currLocation.y - yRand);
            f.setLocation(pos1);
            f.setLocation(pos2);
        }
        f.setLocation(currLocation);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame f = new JFrame("Timer");
        JLabel timeSec = new JLabel("0");
        JLabel timeMin = new JLabel("0");
        JLabel m = new JLabel(":");
        SpinnerNumberModel model = new SpinnerNumberModel(10, 0, 60, 1);
        SpinnerNumberModel model2 = new SpinnerNumberModel(1, 0, 60, 1);
        JSpinner spinSec = new JSpinner(model);
        JSpinner spinMin = new JSpinner(model2);
        JButton reset = new JButton("Start/Reset");
        reset.addActionListener(e -> { 
            startTime = System.currentTimeMillis();
            startReset = true;
        });
        fontSet(reset);
        fontSet(timeSec);
        fontSet(timeMin);
        fontSet(m);
        fontSet(spinSec);
        fontSet(spinMin);
        reset.setBounds(15, 200,250,50);
        timeSec.setBounds(150,80, 100,50);
        timeMin.setBounds(80, 80, 100, 50);
        m.setBounds(120, 75, 100, 50);
        spinSec.setBounds(137, 150, 50, 30);
        spinMin.setBounds(60, 150, 50, 30);
        f.add(timeSec);
        f.add(timeMin);
        f.add(m);
        f.add(reset);
        f.getContentPane().add(spinSec);
        f.add(spinMin);
        f.setSize(300,300);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocation(650, 350);
        f.setAlwaysOnTop(true);
        f.setResizable(false);
        f.setCursor(Cursor.CROSSHAIR_CURSOR);


        timer(timeMin, timeSec, spinSec, spinMin, f);
    }
}
// javac TimeTest.java; java TimeTest
