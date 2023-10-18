package view.venda.paneis;

import controllers.ClienteController;
import controllers.ProdutoController;
import controllers.VendaController;
import controllers.VendaProdutoController;
import model.Cliente;
import model.Produto;
import model.VendaProdutos;
import view.utils.Layout;
import view.venda.ViewVendas;
import view.venda.table_models.TModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CadastroPanel extends JPanel {

    private JLabel codCli, codProd, nomeCli, numVend, nomeProd, qtt, desc, total;
    private JTextField txtCodCli, txtCodProd, txtQtt, txtDesc, txtTotal, txtNumVend;
    private JComboBox produtoBox, clienteBox;
    private JButton add, cancel, novo, save;
    private JTable table;
    private TModel tModel;
    private JScrollPane scrollPane;
    private ProdutoController produtoController = new ProdutoController();
    private ClienteController clienteController = new ClienteController();
    private VendaController vendaController = new VendaController();
    private VendaProdutoController controller = new VendaProdutoController();

    public static Integer mainId;

    Integer idVendaProd = 1;
    ViewVendas viewVendas;

    public CadastroPanel(ViewVendas viewVendas) {
        this.viewVendas = viewVendas;
        initComponent();
        eventos();
        addTOPanel();




        this.setVisible(true);
    }


    public void initComponent(){

        this.setLayout(new GridBagLayout());

        // labels
        codProd = new JLabel("Cod. produto:");
        codCli = new JLabel("Cod. Cliente:");
        desc = new JLabel("Desconto:");
        total = new JLabel("Total:");
        numVend = new JLabel("N. venda:");
        qtt = new JLabel("Quantidade");
        nomeCli = new JLabel("Nome. Cliente:");
        nomeProd = new JLabel("Nome. Produto:");
        Layout.labels(codProd, codCli, desc, total, numVend, qtt, nomeCli, nomeProd);

        // fileds
        txtCodCli = new JTextField(10);
        txtCodProd = new JTextField(10);
        txtQtt = new JTextField(10);
        txtTotal = new JTextField(10);
        txtDesc = new JTextField(10);
        txtNumVend = new JTextField(7){{
            setText(String.valueOf(vendaController.getNextPosition()));
        }};

        mainId = Integer.parseInt(txtNumVend.getText());
        Layout.makeFieldsNotEditabel(txtCodCli, txtCodProd, txtDesc, txtNumVend, txtTotal);

        clienteBox = new JComboBox(new Vector(clienteController.getNomes())){{
            setPreferredSize(new Dimension(460, 20));
            setSelectedIndex(-1);
        }};
        produtoBox = new JComboBox(new Vector(produtoController.getNomes())){{
            setPreferredSize(new Dimension(350, 20));
            setSelectedIndex(-1);
        }};

        // btns
        add = new JButton("Adicionar");
        cancel = new JButton("Cancelar");
        novo = new JButton("Novo");
        save = new JButton("Salvar");

        // table
        tModel = new TModel(controller);
        table = new JTable(tModel){{
            setPreferredScrollableViewportSize(new Dimension(500, 350));
        }};
        scrollPane = new JScrollPane(table){{
            setPreferredSize(new Dimension(690, 300));
        }};

    }

    public void eventos(){
        clienteBox.addItemListener(e -> {
            var index = clienteBox.getSelectedIndex();
            Cliente cliente = clienteController.getByIndex(index);
            txtCodCli.setText(String.valueOf(cliente.getId()));
        });

        produtoBox.addItemListener(e -> {
            var index = produtoBox.getSelectedIndex();
            Produto produto = produtoController.getByIndex(index);
            txtCodProd.setText(String.valueOf(produto.getCodigo()));
        });

        add.addActionListener(e -> {
            var prodId = Integer.parseInt(txtCodProd.getText());
            var idVenda = Integer.parseInt(txtNumVend.getText());
            var qtt = Integer.parseInt(txtQtt.getText());
            var idVendaProd = controller.getTem().size();
            controller.addItem(idVendaProd, prodId, idVenda, qtt);
            clienteBox.setEditable(false);
            table.updateUI();
            updateTotalField();

        });

        save.addActionListener(e -> {
            if(table.getRowCount() != 0){
                var idCli = Integer.parseInt(txtCodCli.getText());
                var idVenda = Integer.parseInt(txtNumVend.getText());
                var tot = Double.parseDouble(txtTotal.getText());
                controller.salvar();
                vendaController.cadastrar(idVenda, idCli, tot, tot, 0.0);
                resetFields();
                clienteBox.setEditable(true);
                Lista.table.updateUI();
            }
        });

        cancel.addActionListener(e -> {
            viewVendas.dispose();
        });

        novo.addActionListener(e -> {
            resetFields();
        });
    }

    private void addTOPanel(){

        // linha0
        Layout.constraints.anchor = GridBagConstraints.WEST;
        Layout.constraints.insets = new Insets(6, 6,5,6);
        Layout.addComponent(this, codCli, 0,0,1,1);
        Layout.addComponent(this, nomeCli, 0, 1, 2,1);
        Layout.addComponent(this, numVend,0,3,2,1);

        // linha 1
        Layout.addComponent(this, txtCodCli, 1,0,1,1);
        Layout.addComponent(this, clienteBox, 1,1,2,1);
        Layout.addComponent(this, txtNumVend, 1,3,2,1);

        //linha 2
        Layout.addComponent(this, codProd, 2,0,1,1);
        Layout.addComponent(this, nomeProd, 2,1,1,1);
        Layout.addComponent(this, qtt, 2,2,1,1);



        // linha 3
        Layout.addComponent(this, txtCodProd, 3,0,1,1);
        Layout.addComponent(this, produtoBox, 3,1,1,1);
        Layout.addComponent(this, txtQtt, 3,2,1,1);
        Layout.addComponent(this, add, 3,3,1,1);

        // linha 4 - 7
        Layout.addComponent(this, scrollPane, 4, 0, 6, 3);

        // linha 8
        Layout.addComponent(this, desc, 8, 2,1,1);
        Layout.addComponent(this, total, 8,3,1,1);

        // linha 9
        Layout.addComponent(this, txtDesc, 9,2,1,1);
        Layout.addComponent(this, txtTotal, 9,3,1,1);

        //inha 10
        Layout.addComponent(this, cancel, 10,0,1,1);
        Layout.addComponent(this, novo, 10,1,1,1);

        Layout.constraints.anchor = GridBagConstraints.EAST;
        Layout.addComponent(this, save, 10,3,1,1);

    }


    public void updateTotalField() {
        double soma = 0.0;
        ArrayList<VendaProdutos> byVenda = controller.getTem();
        for (VendaProdutos vendaProdutos : byVenda) {
            soma += vendaProdutos.getTotal();
        }
        txtTotal.setText(String.valueOf(soma));
    }

    public void resetFields () {
        txtTotal.setText("");
        txtQtt.setText("");
        txtNumVend.setText(String.valueOf(vendaController.getLista().size()));
        table.setModel(new TModel(controller));
        table.updateUI();
    }

}
