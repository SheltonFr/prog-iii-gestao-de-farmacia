package controllers;

import FileManagement.FileManager;
import model.Produto;
import model.Venda;
import model.VendaProdutos;

import java.io.*;
import java.util.ArrayList;

public class VendaProdutoController {

    private String fileName = "vendaProduto.txt";
    private File file = FileManager.createFile(fileName);
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    private ProdutoController produtoController = new ProdutoController();

    ArrayList<VendaProdutos> temp  = new ArrayList<>();


    public ArrayList<VendaProdutos> getLista() {
        ArrayList<VendaProdutos> vendas = new ArrayList<>();
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            vendas = (ArrayList<VendaProdutos>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.getMessage();
        }

        return vendas;
    }

    private boolean saveAtFile(ArrayList<VendaProdutos> vendas) throws IOException {
        objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(vendas);
        objectOutputStream.flush();
        objectOutputStream.close();
        return true;
    }


    public void addItem(Integer id, Integer idProd, Integer idVenda, Integer quantidade){
        Produto produto = produtoController.getById(idProd);
        VendaProdutos vendaProdutos = new VendaProdutos(id, produto.getCodigo(), idVenda, produto.getPreco(), quantidade);
        temp.add(vendaProdutos);
    }
    public void salvar(){
        ArrayList<VendaProdutos> lista = getLista();

        for (VendaProdutos vendaProdutos : temp) {
            lista.add(vendaProdutos);
        }
        temp.clear();

        try {
            saveAtFile(lista);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public ArrayList<VendaProdutos> getByVenda(int vendaId){
        ArrayList<VendaProdutos> lista = getLista();
        ArrayList<VendaProdutos> newList = new ArrayList<>();
        for (VendaProdutos vendaProdutos : lista) {
            if(vendaProdutos.getVendas() == vendaId) {
                newList.add(vendaProdutos);
            }
        }

         return newList;
    }

    public ArrayList<VendaProdutos> getTem() {
        return temp;
    }

    public int getSize(int idVenda) {
        return getByVenda(idVenda).size();
    }

    public void limparLisa(){
        ArrayList<VendaProdutos> lista = getLista();
        lista.clear();
        try {
            saveAtFile(lista);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
