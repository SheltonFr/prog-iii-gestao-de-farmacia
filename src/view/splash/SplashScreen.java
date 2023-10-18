package view.splash;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {
    private int duration;

    public SplashScreen(int duration) {
        this.duration = duration;
        this.setSize(620, 400);
        this.setLocationRelativeTo(null);
    }

    public void showSplash(){
        JPanel content = (JPanel)this.getContentPane();
        content.setBackground(Color.WHITE);
        content.setLayout(new GridLayout(1,1));

        ImageIcon icon = new ImageIcon("C:\\gestao\\splash.jpeg");
        content.add(new JLabel(icon));

        setVisible(true);

        try{
            Thread.sleep(duration);
        } catch (Exception e) {}

        setVisible(false);
    }

    public void showSpalshAndExit(){
        showSplash();

    }

}
