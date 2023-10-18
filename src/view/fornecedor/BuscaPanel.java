package view.fornecedor;

import controllers.FornecedorController;
import farmaciaExceptios.InvalidValueException;
import model.Fornecedor;
import view.fornecedor.table_models.BuscaModel;
import view.fornecedor.table_models.MainModel;
import view.utils.Layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class BuscaPanel extends JPanel {

    private FornecedorController controller;
    public static JTable table;
    private JScrollPane scrollPane;
    private MainModel mainModel;
    private JLabel nome, numero, titulo, nuit;
    private JTextField numField, nomeField, nuitField;
    private JButton buscar, editar, eliminar;
    private JTabbedPane tabbedPane;
    private RegistroPanel regitroPanel;
    public BuscaPanel(FornecedorController controller, JTabbedPane tabbedPane, RegistroPanel regitroPanel){
        this.controller = controller;
        this.tabbedPane = tabbedPane;
        this.regitroPanel = regitroPanel;
        initComponent();
        eventos();
        addToFrame();

    }

    private void initComponent(){
        this.setLayout(new GridBagLayout());
        titulo = new JLabel("Listagem de Fornecedores"){{
            setAlignmentX(SwingConstants.CENTER);
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(new Font(Font.SERIF, Font.BOLD, 25));
            setForeground(Color.RED);
        }};



        numero = new JLabel("Numero: ");
        nome = new JLabel("Nome: ");
        nuit = new JLabel("Nuit: ");
        numField = new JTextField(5);
        nomeField = new JTextField(17);
        nuitField = new JTextField(15);
        buscar = new JButton("Pesquisar");
        editar = new JButton("Editar");
        eliminar = new JButton("Eliminar");

        editar.setEnabled(false);
        eliminar.setEnabled(false);


        Layout.labels(nome, numero, nuit);

        mainModel = new MainModel(controller);
        table = new JTable(mainModel);
        table.setPreferredScrollableViewportSize(new Dimension(450,190));
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(550,200));


    }

    public void eventos(){

        buscar.addActionListener(e ->{
            String num = numField.getText();
            String nome = nomeField.getText();
            String nuit = nuitField.getText();
            try {
                Fornecedor fornecedor = controller.validateInputsAndSearchFornecedor(num, nome, nuit);

                if(fornecedor != null){
                    BuscaModel buscaModel = new BuscaModel(fornecedor);
                    table.setModel(buscaModel);
                    table.updateUI();
                } else {
                    JOptionPane.showMessageDialog(this, "Armazem nao identificado!");
                    table.setModel(mainModel);
                    table.updateUI();
                }
            } catch (InvalidValueException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        });

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (table.getSelectedRowCount() == 1){
                        editar.setEnabled(true);
                        eliminar.setEnabled(true);
                    } else {
                        editar.setEnabled(false);
                        eliminar.setEnabled(false);
                    }
                } else {
                    editar.setEnabled(false);
                    eliminar.setEnabled(false);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        editar.addActionListener(e -> {
            int id = table.getSelectedRow();
            String nome = table.getValueAt(id, 1).toString();
            String nuit = table.getValueAt(id, 2).toString();
            tabbedPane.setSelectedIndex(0);
            regitroPanel.fillFields(id + 1, nome, nuit);
            editar.setEnabled(false);
            eliminar.setEnabled(false);
        });

        eliminar.addActionListener(e -> {
            int id = table.getSelectedRow();

            try {
                controller.deleteById(id);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            table.updateUI();
            editar.setEnabled(false);
            eliminar.setEnabled(false);
        });
    }

    private void addToFrame(){
        Layout.constraints.fill = GridBagConstraints.HORIZONTAL;
        Layout.constraints.insets = new Insets(0,5,8,10);
        Layout.constraints.anchor = GridBagConstraints.CENTER;
        Layout.addComponent(this, titulo, 0,0,6,1);

        Layout.constraints.anchor = GridBagConstraints.EAST;
        Layout.constraints.insets.top = 20;
        Layout.addComponent(this, numero, 1,0,1,1);
        Layout.addComponent(this, numField,1,1,1,1);
        Layout.addComponent(this, nome,1,2,1,1);
        Layout.addComponent(this, nomeField,1,3,1,1);
        Layout.addComponent(this, nuit,1,4,1,1);
        Layout.addComponent(this, nuitField,1,5,1,1);

        Layout.constraints.anchor = GridBagConstraints.CENTER;
        Layout.constraints.fill = GridBagConstraints.NONE;
        Layout.addComponent(this, buscar,2, 0 ,6 ,1);


        Layout.constraints.fill = GridBagConstraints.BOTH;
        Layout.addComponent(this, scrollPane,3,0,6,3);

        Layout.constraints.fill = GridBagConstraints.HORIZONTAL;

        Layout.addComponent(this, eliminar,7,4,1,1);
        Layout.addComponent(this, editar,7,5,1,1);

    }


}
