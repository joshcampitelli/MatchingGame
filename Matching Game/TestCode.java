import java.text.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.util.TimerTask;
import java.util.Calendar;

/**
 * Write a description of class TestCode here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCode {
    public static void main(String... args) throws InterruptedException {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        int interval = 1000; // 1000 ms

        new Timer(interval, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Calendar now = Calendar.getInstance();
                    System.out.println(dateFormat.format(now.getTime()));
                }
            }).start();

        Thread.currentThread().join();
    }
}
