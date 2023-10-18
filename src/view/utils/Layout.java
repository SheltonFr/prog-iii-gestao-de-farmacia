package view.utils;

import javax.swing.*;
import java.awt.*;

public class Layout {

    public static GridBagConstraints constraints = new GridBagConstraints();

    public static void addComponent(Container container, Component component, int row, int column, int width, int height) {
        constraints.gridy = row;
        constraints.gridx = column;
        constraints.gridwidth = width;
        constraints.gridheight = height;

        container.add(component, constraints);
    }


    // configurar labels
    public static void labels(JLabel... labels){
        for (JLabel label : labels) {
            label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        }
    }

    public static void configTitleLbls(JLabel label){
        label.setAlignmentX(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        label.setForeground(Color.RED);
    }


    // limpar Fields
    public static void cleanFields(JTextField...fields){
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    public static void makeFieldsNotEditabel(JTextField...fields){
        for (JTextField fld:
             fields) {
            fld.setEditable(false);
        }
    }

}
