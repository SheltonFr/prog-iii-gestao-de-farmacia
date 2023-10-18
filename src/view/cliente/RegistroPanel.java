package view.cliente;

import controllers.ClienteController;
import farmaciaExceptios.ClienteAlreadyExistsException;
import farmaciaExceptios.InvalidValueException;
import view.utils.Layout;

import javax.swing.*;
import java.awt.*;

public class RegistroPanel extends JPanel {

    private JLabel numero, nome, bi, titulo;
    private JTextField numField, nomeField, biField;
    private JButton save, update, clear;
    ClienteController controller;

    public RegistroPanel(ClienteController controller){
        this.controller = controller;
        initComponent();
        events();
        addToPanel();
    }
    public void initComponent() {

        this.setLayout(new GridBagLayout());

        titulo = new JLabel("Registro de Clientes"){{
            setAlignmentX(SwingConstants.CENTER);
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(new Font(Font.SERIF, Font.BOLD, 25));
            setForeground(Color.RED);
        }};

        numero = new JLabel("Numero: ");
        nome = new JLabel("Nome: ");
        bi = new JLabel("bi: ");
        numField = new JTextField(){{
            setEditable(false);
            setText(String.valueOf(controller.getNextPosition()));
        }};


        nomeField = new JTextField();
        biField = new JTextField();
        save = new JButton("Gravar");
        update = new JButton("Actulizar");
        update.setEnabled(false);
        clear = new JButton("Limpar");

        Layout.labels(nome, numero, bi);

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
        Layout.addComponent(this, bi, 3,0,1,1);
        Layout.addComponent(this,biField,3,1,2,1);
        Layout.addComponent(this, update,4,0,1,1);
        Layout.addComponent(this,save,4,1,1,1);
        Layout.addComponent(this,clear,4,2,1,1);
    }


    // programando eventos
    public void events(){
        save.addActionListener(e -> {
            var nomeCliente = nomeField.getText();
            var numeroCLiente = controller.getNextPosition();
            var biCliente = biField.getText();

           try {
                controller.cadastrar(numeroCLiente, nomeCliente, biCliente);
                JOptionPane.showMessageDialog(this, "Cadastrado com sucesso!");
                BuscaPanel.table.updateUI();
            } catch (ClienteAlreadyExistsException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } 

            Layout.cleanFields(nomeField, biField);
            updateNumberInput();
        });

        update.addActionListener(e -> {

            int numero = Integer.parseInt(numField.getText());
            String nomeFor = nomeField.getText();
            String biFor = biField.getText();

            try {
                controller.actualizar(numero -1, nomeFor, biFor);
                JOptionPane.showMessageDialog(this,"Actualizado com sucesso");
                BuscaPanel.table.updateUI();
            } catch (InvalidValueException ex) {
                throw new RuntimeException(ex);
            }

            Layout.cleanFields(nomeField, biField);
            updateNumberInput();

            update.setEnabled(false);
            save.setEnabled(true);
        });


        clear.addActionListener(e ->{
            Layout.cleanFields(nomeField, biField);
            save.setEnabled(true);
            update.setEnabled(false);
            updateNumberInput();
        });


    }

    // actualoizar o valor no imput com o numero de armazem
    public void updateNumberInput() {
        this.numField.setText(String.valueOf(controller.getNextPosition()));
        validate();
    }


    public void fillFields(int numero, String nome, String bi){
        numField.setText(String.valueOf(numero));
        nomeField.setText(nome);
        biField.setText(bi);

        save.setEnabled(false);
        update.setEnabled(true);
    }


}
