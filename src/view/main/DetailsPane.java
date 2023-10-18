package view.main;

import model.Administrador;
import view.utils.Layout;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DetailsPane extends JPanel {

    Administrador administrador;
    private JLabel greeting, perfil, timeLbl, time;
    private Timer timer;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    public DetailsPane(Administrador administrador){
        this.administrador = administrador;
        this.setLayout(new GridBagLayout());

        initComponent();
        addToPanel();


    }

    public void initComponent() {
        perfil = new JLabel("Administrador!");
        greeting = new JLabel("Olá " + administrador.getNome() + "!");
        timeLbl = new JLabel("Horário: ");
        time = new JLabel("");



        Layout.configTitleLbls(perfil);
        Layout.labels(greeting, timeLbl, time);
    }


    public void addToPanel(){
        Layout.constraints.insets = new Insets(20,10,24,10);
        Layout.addComponent(this, perfil, 0,0,1,1);
        Layout.addComponent(this, greeting, 1,0,2,1);
        Layout.addComponent(this, time, 2,0,1,1);

        timer = new Timer(1000, e -> {
            LocalDateTime dateTime = LocalDateTime.now();
            var hh = dateTime.getHour();
            var mm = dateTime.getMinute();
            var ss = dateTime.getSecond();
            time.setText("Horário: " + String.valueOf(hh) + " : " + String.valueOf(mm) + " : " + String.valueOf(ss));
            time.validate();
            time.updateUI();
        });
        timer.start();



    }
}
