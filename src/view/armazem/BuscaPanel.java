package view.armazem;

import controllers.ArmazemController;
import farmaciaExceptios.InvalidValueException;
import model.Armazem;
import view.armazem.table_models.BuscaModel;
import view.armazem.table_models.MainModel;
import view.utils.Layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class BuscaPanel extends JPanel {

    private ArmazemController controller;
    public static JTable table;
    private JScrollPane scrollPane;
    private MainModel mainModel;
    private JLabel nome, numero, titulo;
    private JTextField numField, nomeField;
    private JButton buscar, editar, eliminar;
    private JTabbedPane tabbedPane;
    private RegistroPanel registroPanel;
    public BuscaPanel(ArmazemController controller, JTabbedPane tabbedPane, RegistroPanel registroPanel){
        this.controller = controller;
        this.registroPanel = registroPanel;
        this.tabbedPane = tabbedPane;
        initComponent();
        eventos();
        addToFrame();


    }

    private void initComponent(){
        this.setLayout(new GridBagLayout());
        titulo = new JLabel("Listagem de Armazens"){{
            setAlignmentX(SwingConstants.CENTER);
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(new Font(Font.SERIF, Font.BOLD, 25));
            setForeground(Color.RED);
        }};

        numero = new JLabel("Numero: ");
        nome = new JLabel("Nome: ");
        numField = new JTextField(5);
        nomeField = new JTextField(17);
        buscar = new JButton("Pesquisar");
        eliminar = new JButton("Eliinar");
        editar = new JButton("Editar");

        editar.setEnabled(false);
        eliminar.setEnabled(false);

        Layout.labels(nome, numero);

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
            try {
                Armazem armazem = controller.validateInputsAndSearchArmazem(num, nome);

                if(armazem != null){
                    BuscaModel buscaModel = new BuscaModel(armazem);



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
            tabbedPane.setSelectedIndex(0);
            registroPanel.fillFields(id + 1, nome);
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
        Layout.constraints.insets = new Insets(0,10,8,10);
        Layout.constraints.anchor = GridBagConstraints.CENTER;
        Layout.addComponent(this, titulo, 0,0,5,1);

        Layout.constraints.anchor = GridBagConstraints.EAST;
        Layout.constraints.insets.top = 20;
        Layout.addComponent(this, numero, 1,0,1,1);
        Layout.addComponent(this, numField,1,1,1,1);
        Layout.addComponent(this, nome,1,2,1,1);
        Layout.addComponent(this, nomeField,1,3,1,1);
        Layout.addComponent(this, buscar,1, 4 ,1 ,1);


        Layout.constraints.fill = GridBagConstraints.BOTH;
        Layout.addComponent(this, scrollPane,2,0,5,3);


        Layout.constraints.fill = GridBagConstraints.HORIZONTAL;

        Layout.addComponent(this, eliminar,6,3,1,1);
        Layout.addComponent(this, editar,6,4,1,1);

    }


}
