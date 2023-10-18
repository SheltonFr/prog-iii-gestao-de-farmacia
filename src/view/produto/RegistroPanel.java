package view.produto;

import controllers.ArmazemController;
import controllers.FornecedorController;
import controllers.ProdutoController;
import farmaciaExceptios.InvalidValueException;
import view.utils.Layout;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.Vector;

public class RegistroPanel extends JPanel {

    private JLabel numero, nome, quantidade, fornecedor, armazem, stock, titulo, validade, preco;
    private JTextField numField, nomeField, quantField, stockField, precoField;
    private JFormattedTextField validadeField;
    private JComboBox<String> comboArm, comboForn;
    private MaskFormatter mask;
    private JButton save, update, clear;
    ProdutoController controller;
    ArmazemController armazemController = new ArmazemController();
    FornecedorController fornecedorController = new FornecedorController();

    public RegistroPanel(ProdutoController controller){
        this.controller = controller;
        initComponent();
        events();
        addToPanel();
    }
    public void initComponent() {

        this.setLayout(new GridBagLayout());

        titulo = new JLabel("Registro de Produtos");

        numero = new JLabel("Numero: ");
        nome = new JLabel("Nome: ");
        armazem = new JLabel("Armazem");
        stock = new JLabel("Stock minimo");
        fornecedor = new JLabel("Fornecedor");
        quantidade = new JLabel("Quantidade");
        validade = new JLabel("Validade");
        preco = new JLabel("Preco:");

        precoField = new JTextField();
        try {
            mask = new MaskFormatter("##/##/####");
            mask.setPlaceholder("_");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        numField = new JTextField(){{
            setEditable(false);
            setText(String.valueOf(controller.getNextPosition()));
        }};


        nomeField = new JTextField();
        quantField = new JTextField();
        stockField = new JTextField();
        comboArm = new JComboBox(new Vector(armazemController.getNomes()));
        comboForn = new JComboBox(new Vector(fornecedorController.getNames()));
        validadeField = new JFormattedTextField(mask);

        save = new JButton("Gravar");
        update = new JButton("Actulizar");
        update.setEnabled(false);
        clear = new JButton("Limpar");

        Layout.configTitleLbls(titulo);
        Layout.labels(nome, numero, quantidade, stock, armazem, fornecedor, preco, validade);

    }
    public void addToPanel() {
        Layout.constraints.fill = GridBagConstraints.HORIZONTAL;
        Layout.constraints.insets = new Insets(0,7,8,10);

        Layout.constraints.anchor = GridBagConstraints.CENTER;
        Layout.addComponent(this, titulo,0,0,3,1);

        Layout.constraints.anchor = GridBagConstraints.EAST;
        Layout.constraints.insets.top = 30;
        Layout.addComponent(this, numero, 1, 0,1 ,1);
        Layout.addComponent(this, numField, 1,1,2,1);
        Layout.constraints.insets.top = 20;

        Layout.addComponent(this, quantidade, 2,0,1,1);
        Layout.addComponent(this, quantField, 2,1,2,1);
        Layout.addComponent(this, stock, 3,0,1,1);
        Layout.addComponent(this, stockField,3,1,2,1);
        Layout.addComponent(this, nome, 4, 0,1,1);
        Layout.addComponent(this, nomeField, 4, 1,2,1);
        Layout.addComponent(this, preco,5,0,1,1);
        Layout.addComponent(this, precoField,5,1,2,1);
        Layout.addComponent(this, validade,6,0,1,1);
        Layout.addComponent(this, validadeField,6,1,2,1);
        Layout.addComponent(this, armazem, 7, 0,1,1);
        Layout.addComponent(this, comboArm, 7, 1,2,1);
        Layout.addComponent(this, fornecedor, 8, 0, 1,1);
        Layout.addComponent(this, comboForn, 8,1,2,1);

        Layout.constraints.insets.top = 20;
        Layout.addComponent(this, update, 9, 0, 1,1);
        Layout.addComponent(this, save, 9, 1, 1,1);
        Layout.addComponent(this, clear, 9, 2, 1,1);

    }


    // programando eventos
    public void events(){
        save.addActionListener(e -> {

            if(armazemController.getSize() != 0 && fornecedorController.getSize() != 0){
                int number = Integer.parseInt(numField.getText());
                var qtt = quantField.getText();
                var stk = stockField.getText();
                var nome = nomeField.getText();
                int idArmazem = comboArm.getSelectedIndex();
                int idFornecedor = comboForn.getSelectedIndex();
                var preco = precoField.getText();
                String valdd = validadeField.getText();


                try {
                    controller.cadastrar(number, qtt, stk, nome, idArmazem, idFornecedor, valdd, preco);
                    JOptionPane.showMessageDialog(this, "Cadastrado com sucesso!");
                    BuscaPanel.table.updateUI();
                } catch (InvalidValueException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }

                Layout.cleanFields(nomeField, quantField, stockField, precoField, validadeField);
                updateNumberInput();
            } else {
                JOptionPane.showMessageDialog(this, "Não foi encontrado nenhum Fornecedor/Armazem selecionado!");
            }
        });

        update.addActionListener(e -> {
            int numero = Integer.parseInt(numField.getText());
            var qtt = quantField.getText();
            var stk = stockField.getText();
            var nome = nomeField.getText();
            int idArmazem = comboArm.getSelectedIndex();
            int idFornecedor = comboForn.getSelectedIndex();


            try {
                controller.actualizar(numero -1, nome, qtt, stk, idArmazem, idFornecedor);
                JOptionPane.showMessageDialog(this,"Actualizado com sucesso");
                view.fornecedor.BuscaPanel.table.updateUI();
            } catch (InvalidValueException ex) {
                throw new RuntimeException(ex);
            }

            Layout.cleanFields(nomeField, quantField, stockField);
            updateNumberInput();

            update.setEnabled(false);
            save.setEnabled(true);
        });


        clear.addActionListener(e -> {
            Layout.cleanFields(nomeField, stockField, quantField);
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



    public void fillFields(int numero, String nome, int stock, int quant){
        numField.setText(String.valueOf(numero));
        nomeField.setText(nome);
        stockField.setText(String.valueOf(stock));
        quantField.setText(String.valueOf(quant));

        comboForn.setSelectedIndex(controller.getByIndex(numero - 1).getFornecedor());
        comboArm.setSelectedIndex(controller.getByIndex(numero - 1).getArmazem());
        save.setEnabled(false);
        update.setEnabled(true);
    }


}
