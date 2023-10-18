package controllers;

import FileManagement.FileManager;
import farmaciaExceptios.InvalidValueException;
import model.Produto;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;

public class ProdutoController {
    private String filename = "produtos.txt";
    private File file = FileManager.createFile(filename);
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);


    public boolean saveAtFile(ArrayList<Produto> produtos) throws IOException {
        objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(produtos);
        objectOutputStream.flush();
        objectOutputStream.close();
        return true;
    }

    public ArrayList<Produto> getLista() {
        ArrayList<Produto> produtos = new ArrayList<>();

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            produtos = (ArrayList<Produto>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.getMessage();
        }

        return produtos;
    }

    public Produto getById(int id){
        Produto Produto = null;
        ArrayList<Produto> lista = getLista();

        for (Produto prod : lista) {
            if(prod.getCodigo() == id){
                Produto = prod;
                break;
            }
        }

        return Produto;
    }

    public Produto getByName(String name){
        Produto Produto = null;
        ArrayList<Produto> lista = getLista();

        for (Produto prod : lista) {
            if(prod.getNome().contains(name) || prod.getNome().equalsIgnoreCase(name)){
                Produto = prod;
                break;
            }
        }

        return Produto;
    }

    public Produto getByIdAndByName(int id, String name){
        Produto Produto = null;
        ArrayList<Produto> lista = getLista();

        for (Produto prod : lista) {
            if(prod.getCodigo() == id && (prod.getNome().equalsIgnoreCase(name)||prod.getNome().contains(name))){
                Produto = prod;
                break;
            }
        }

        return Produto;
    }

   

    public Produto validateInputsAndSearchProduto(String id, String name, String nuit) throws InvalidValueException {
        if(!id.isBlank() && !name.isBlank()){
            try{
                int numero = Integer.parseInt(id);
                return getByIdAndByName(numero, name);
            } catch (NumberFormatException e){
                throw new InvalidValueException("Insira apenas numeros no campo Numero!");
            }
        }

        if(id.isBlank() && !name.isBlank()){
            return getByName(name);
        }

        if(!id.isBlank() && name.isBlank()){
            try{
                int numero = Integer.parseInt(id);
                return getById(numero);
            } catch (NumberFormatException e){
                throw new InvalidValueException("Insira apenas numeros no campo Numero!");
            }
        }


        return null;
    }

    public void cadastrar(int numero, String quantidade, String stock, String nome, int idArmazem, int idFornecedor, String validade, String preco)
            throws InvalidValueException{

        if(!quantidade.isBlank() && !stock.isBlank() && !nome.isBlank()){
            try {
                ArrayList<Produto> produtos = getLista();
                LocalDate dateValidade = null;
                try {
                    dateValidade = LocalDate.parse(validade, formatter);
                } catch (DateTimeParseException e){
                    throw new InvalidValueException("Data Invalida!");
                }


                if(dateValidade.isBefore(LocalDate.now())){
                    throw new InvalidValueException("Nao pode inserir produtos fora de validade!");
                }

                int intQtt = Integer.parseInt(quantidade);
                int intStk = Integer.parseInt(stock);
                double price = Double.parseDouble(preco);

                if(intStk > intQtt) throw new InvalidValueException("A quantidade não pode ser inferior ao numero minimo de stock!");

                Produto produto = new Produto(numero, intStk, idArmazem, intQtt, nome, idFornecedor, LocalDateTime.now(), dateValidade, price);

                produtos.add(produto);
                try {
                    saveAtFile(produtos);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }catch (NumberFormatException e){
                throw new InvalidValueException("Insira valores validos!");
            }
        } else {
            throw new InvalidValueException("Insira valores validos!");
        }

    }

    public void limparLista() {
        ArrayList<Produto> Produtos = getLista();
        Produtos.clear();
        try {
            saveAtFile(Produtos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public  boolean actualizar(int id, String nome, String quantidade, String stock, int idArmazem, int idFornecedor ) throws InvalidValueException {
        ArrayList<Produto> produtos = getLista();
        if(!nome.isBlank() && !quantidade.isBlank() && !stock.isBlank()){
            produtos.get(id).setNome(nome);
            int qtt = Integer.parseInt(quantidade);
            int stk = Integer.parseInt(stock);

            if(stk < qtt) throw new InvalidValueException("A quantidade não pode ser inferior ao numero minimo de stock!");
            try{
                produtos.get(id).setQuantidade(qtt);
                produtos.get(id).setMinStock(stk);
                produtos.get(id).setArmazem(idArmazem);
                produtos.get(id).setFornecedor(idFornecedor);
            } catch (NumberFormatException e){
                throw new InvalidValueException("Preencha os campos Correctamente!");
            }

            try {
                saveAtFile(produtos);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }

        throw new InvalidValueException("Preencha os campos Corectamente!");

    }

    public int getNextPosition() {
        ArrayList<Produto> Produtos = (ArrayList<Produto>) getLista();
        return Produtos.size() + 1;
    }

    public Produto getByIndex(int index){
        return getLista().get(index);
    }

    public int getSize(){
        return getLista().size();
    }

    public void deleteById(int id) throws IOException {
        ArrayList<Produto> produtos = getLista();
        produtos.remove(id);
        saveAtFile(produtos);
    }

    public ArrayList<String> getNomes() {
        ArrayList<String> nomes = new ArrayList<>();
        ArrayList<Produto> lista = getLista();

        for (Produto p:
             lista) {
            nomes.add(p.getNome());
        }


        return nomes;
    }



}
