package view.venda;

import controllers.ClienteController;
import controllers.ProdutoController;
import view.venda.paneis.CadastroPanel;
import view.venda.paneis.Lista;

import javax.swing.*;

public class ViewVendas extends JFrame {

    private ProdutoController produtoController = new ProdutoController();
    private ClienteController clienteController = new ClienteController();

    private JTabbedPane tabbedPane;
    private CadastroPanel cadastroPanel;
    private Lista listagem;
    public ViewVendas() {
        initComponent();

        this.setVisible(true);
    }


    public void initComponent(){
        this.setTitle("Vendas");
        this.setLocationRelativeTo(null);
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cadastroPanel = new CadastroPanel(this);

        tabbedPane = new JTabbedPane();
        listagem = new Lista(tabbedPane, cadastroPanel);
        tabbedPane.addTab("Cadastro", cadastroPanel);
        tabbedPane.addTab("Listagem", listagem);
        this.add(tabbedPane);
    }


    public static void main(String[] args) {
        new ViewVendas();
    }
}
