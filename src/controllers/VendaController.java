package controllers;

import FileManagement.FileManager;
import model.Venda;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class VendaController {

    private String fileName = "vendas.txt";
    private File file = FileManager.createFile(fileName);
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;



    public ArrayList<Venda> getLista() {
        ArrayList<Venda> vendas = new ArrayList<>();

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            vendas = (ArrayList<Venda>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.getMessage();
        }

        return vendas;
    }

    private boolean saveAtFile(ArrayList<Venda> vendas) throws IOException {
        objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(vendas);
        objectOutputStream.flush();
        objectOutputStream.close();
        return true;
    }

    public void cadastrar(Integer id, Integer idCli, Double bruto, Double liquido, Double desconto){
        ArrayList<Venda> vendas = getLista();
        Venda venda = new Venda(id, idCli, LocalDate.now(), bruto, liquido, 0.0);
        vendas.add(venda);


        try {
            saveAtFile(vendas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void limparLista() {
        ArrayList<Venda> lista = getLista();
        lista.clear();
        try {
            saveAtFile(lista);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public int getNextPosition() {
        ArrayList<Venda> vendas = getLista();
        return vendas.size() + 1;
    }

    public static void main(String[] args) {
        new VendaController().limparLista();
    }
}
