package view.venda.paneis;

import view.utils.Layout;
import view.venda.table_models.ListaModel;

import javax.swing.*;
import java.awt.*;

public class Lista extends JPanel {

    private JLabel pesquisa;
    private JTextField pesField;
    private JButton search, delete, edit;
    private ListaModel model;
    public static JTable table;
    private JScrollPane scrollPane;

    private JTabbedPane tabbedPane;
    private CadastroPanel cadastroPanel;

    public Lista(JTabbedPane tabbedPane, CadastroPanel cadastroPanel) {
        this.setLayout(new GridBagLayout());
        this.tabbedPane = tabbedPane;
        this.cadastroPanel = cadastroPanel;
        initComponent();
        addToPanel();
    }

    public void initComponent(){
        pesquisa = new JLabel("Pesquisa: ");
        Layout.labels(pesquisa);
        pesField = new JTextField(){{
            setPreferredSize(new Dimension(340, 20));
        }};
        search = new JButton("Pesquisar");
        edit = new JButton("Editar");
        delete = new JButton("Eliminar");

        model = new ListaModel();
        table = new JTable(model);
        scrollPane = new JScrollPane(table){{
            setPreferredSize(new Dimension(500, 350));
        }};
    }

    private void addToPanel(){
        // linha 0
        Layout.addComponent(this, pesquisa, 0,0,1,1);

        // linha 1
        Layout.addComponent(this, pesField, 1,0,2,1);
        Layout.addComponent(this, search, 1,3,1,1);

        // linha 2
        Layout.addComponent(this, scrollPane, 2,0,4, 3);

        // linha 6
        Layout.constraints.anchor = GridBagConstraints.WEST;
        Layout.addComponent(this, delete, 6,0,1,1);
        Layout.addComponent(this, edit,6,1,1,1);
    }

}
