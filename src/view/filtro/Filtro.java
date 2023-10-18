package view.filtro;

import view.filtro.table_models.StockModel;
import view.filtro.table_models.ValidadeModel;
import view.utils.Layout;

import javax.swing.*;
import java.awt.*;

public class Filtro  extends JFrame {

    private JLabel titulo, descricao;
    private JButton minStock, validade;
    private JTable table;
    private JScrollPane scrollPane;

    private StockModel stockModel;
    private ValidadeModel validadeModel;
    private String stockText, valdiadeText;

    public Filtro(){
        stockText = "Produtos cujo stock minino esta abaixo do previsto!";
        valdiadeText = "Produtos proximos de vencer a validade!";
        initComponent();
        addComponents();
        this.setVisible(true);
    }

    public void initComponent(){
        this.setSize(900, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setLocationRelativeTo(null);



        titulo = new JLabel("Tela de Filtragem");
        descricao = new JLabel(stockText);
        Layout.labels(descricao);
        Layout.configTitleLbls(titulo);

        minStock = new JButton("Abaixo do stock");
        validade = new JButton("Proximo da validade");

        minStock.addActionListener(e -> {
            table.setModel(new StockModel());
            descricao.setText(stockText);
        });

        validade.addActionListener(e -> {
            table.setModel(new ValidadeModel());
            descricao.setText(valdiadeText);
        });

        stockModel = new StockModel();
        table = new JTable(stockModel){{
            setPreferredScrollableViewportSize(new Dimension(450,190));
        }};
        scrollPane = new JScrollPane(table){{
            setPreferredSize(new Dimension(550,200));
        }};



    }

    public void addComponents(){
        Layout.constraints.insets = new Insets(30,0,30,0);
        Layout.addComponent(this, titulo,0,0,2,1);
        Layout.constraints.insets.bottom = 20;
        Layout.addComponent(this, descricao,1,0,2,1);
        Layout.addComponent(this, scrollPane,2,0,2,3);

        Layout.constraints.anchor = GridBagConstraints.EAST;
        Layout.addComponent(this, minStock,5,0,1,1);
        Layout.addComponent(this, validade,5,1  ,1,1);
    }

    public static void main(String[] args) {
        new Filtro();
    }
}
