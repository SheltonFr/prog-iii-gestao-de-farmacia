package view.armazem;

import controllers.ArmazemController;
import farmaciaExceptios.ArmazemAlreadyExistsException;
import farmaciaExceptios.InvalidValueException;
import view.utils.Layout;

import javax.swing.*;
import java.awt.*;

public class RegistroPanel extends JPanel {

    private JLabel numero, nome, titulo;
    private JTextField numField, nomeField;
    private JButton save, update, clear;
    ArmazemController controller;

    public RegistroPanel(ArmazemController controller){
        this.controller = controller;
        initComponent();
        events();
        addToPanel();
    }
    public void initComponent() {

        this.setLayout(new GridBagLayout());

        titulo = new JLabel("Registro de Armazem"){{
            setAlignmentX(SwingConstants.CENTER);
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(new Font(Font.SERIF, Font.BOLD, 25));
            setForeground(Color.RED);
        }};

        numero = new JLabel("Numero: ");
        nome = new JLabel("Nome: ");
        numField = new JTextField(){{
            setEditable(false);
            setText(String.valueOf(controller.getNextPosition()));
        }};


        nomeField = new JTextField();
        save = new JButton("Gravar");
        update = new JButton("Actulizar");
        update.setEnabled(false);
        clear = new JButton("Limpar");

        Layout.labels(nome, numero);

    }

    public void addToPanel() {
        Layout.constraints.fill = GridBagConstraints.HORIZONTAL;
        Layout.constraints.insets = new Insets(0,10,8,10);

        Layout.constraints.anchor = GridBagConstraints.CENTER;
        Layout.addComponent(this, titulo,0,0,3,1);

        Layout.constraints.anchor = GridBagConstraints.EAST;

        Layout.constraints.insets.top = 60;
        Layout.addComponent(this,numero,1,0,1,1);
        Layout.addComponent(this,numField,1,1,1,1);

        Layout.constraints.insets.top = 20;
        Layout.addComponent(this,nome,2,0,1,1);
        Layout.addComponent(this,nomeField,2,1,2,1);
        Layout.addComponent(this, update,3,0,1,1);
        Layout.addComponent(this,save,3,1,1,1);
        Layout.addComponent(this,clear,3,2,1,1);
    }


    // programando eventos
    public void events(){
        save.addActionListener(e -> {
            var nomeArmazem = nomeField.getText();
            var numeroArmazem = controller.getNextPosition();

            try {
                controller.cadastrar(numeroArmazem, nomeArmazem);
                JOptionPane.showMessageDialog(this, "Cadastrado com sucesso!");
                BuscaPanel.table.updateUI();
            } catch (ArmazemAlreadyExistsException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (InvalidValueException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }

            Layout.cleanFields(nomeField);
            updateNumberInput();
        });

        update.addActionListener(e -> {

            int numero = Integer.parseInt(numField.getText());
            String nomeFor = nomeField.getText();

            try {
                controller.actualizar(numero -1, nomeFor);
                JOptionPane.showMessageDialog(this,"Actualizado com sucesso");
                BuscaPanel.table.updateUI();
            } catch (InvalidValueException ex) {
                throw new RuntimeException(ex);
            }

            Layout.cleanFields(nomeField);
            updateNumberInput();

            update.setEnabled(false);
            save.setEnabled(true);
        });

        clear.addActionListener(e ->{
            Layout.cleanFields(nomeField);
            save.setEnabled(true);
            update.setEnabled(false);
            updateNumberInput();
        });


    }


    public void updateNumberInput() {
        this.numField.setText(String.valueOf(controller.getNextPosition()));
        validate();
    }

    public void fillFields(int numero, String nome){
        numField.setText(String.valueOf(numero));
        nomeField.setText(nome);

        save.setEnabled(false);
        update.setEnabled(true);
    }


}
