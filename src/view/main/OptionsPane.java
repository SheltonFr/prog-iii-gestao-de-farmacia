package view.main;

import view.administrador.CadastroAdmin;
import view.administrador.Login;
import view.armazem.ArmazemFrame;
import view.cliente.RegistroCliente;
import view.filtro.Filtro;
import view.fornecedor.RegistroFornecedor;
import view.produto.RegistroProduto;
import view.utils.Layout;
import view.venda.ViewVendas;

import javax.swing.*;
import java.awt.*;

public class OptionsPane extends JPanel {

    private JButton armazens, produtos, fornecedores, filtro, clientes, vendas;

    public OptionsPane(){
        initComponent();
        eventos();
        addToPanel();
    }

    public void initComponent() {

        this.setLayout(new GridBagLayout());

        produtos = new JButton("Produtos"){{
            setToolTipText("Clique para operar com produtos!");
        }};
        armazens = new JButton("Armazens"){{
            setToolTipText("Clique para operar com armazens!");
        }};
        fornecedores = new JButton("Forncecedores"){{
            setToolTipText("Clique para operar com fornecedores!");
        }};
        filtro = new JButton("Filtro"){{
            setToolTipText("Clique para operar com filtros!");
        }};
        clientes = new JButton("Clientes"){{
            setToolTipText("Clique para operar com clientes!");
        }};
        vendas = new JButton("Vendas"){{
            setToolTipText("Clique para operar com vendas!");
        }};
    }

    public void eventos(){
        produtos.addActionListener(e -> {
            new RegistroProduto();
        });

        filtro.addActionListener(e -> {
            new Filtro();
        });
        armazens.addActionListener(e -> {
            new ArmazemFrame();
        });

        fornecedores.addActionListener(e->{
            new RegistroFornecedor();
        });

        vendas.addActionListener(e -> {
            new ViewVendas();
        });
        clientes.addActionListener(e -> {
            new RegistroCliente();
        });
    }

    public void addToPanel(){

        Layout.constraints.anchor = GridBagConstraints.CENTER;
        Layout.constraints.insets = new Insets(15,15,20,8);
        Layout.constraints.fill = GridBagConstraints.BOTH;

        Layout.addComponent(this, clientes, 0,0,1,1);
        Layout.addComponent(this, armazens, 0,1,1,1);
        Layout.addComponent(this, fornecedores, 1,0,1,1);
        Layout.addComponent(this, produtos, 1,1,1,1);
        Layout.addComponent(this, vendas, 2,0,1,1);
        Layout.addComponent(this, filtro, 2,1,1,1);

    }
}
