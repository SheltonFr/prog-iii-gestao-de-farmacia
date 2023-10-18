package controllers;

import FileManagement.FileManager;
import farmaciaExceptios.ArmazemAlreadyExistsException;
import farmaciaExceptios.InvalidValueException;
import model.Armazem;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ArmazemController {

    private String filename = "armazens.txt";
    private File file = FileManager.createFile(filename);
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private ArrayList<Armazem> armazems;

    public boolean saveAtFile(ArrayList<Armazem> armazens) throws IOException {
        objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(armazens);
        objectOutputStream.flush();
        objectOutputStream.close();
        return true;
    }

    public ArrayList<Armazem> getLista() {
        ArrayList<Armazem> armazens = new ArrayList<>();

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            armazens = (ArrayList<Armazem>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.getMessage();
        }

        return armazens;
    }

    public ArrayList<String> getNomes(){
        ArrayList<Armazem> lista = getLista();
        ArrayList<String> nomes = new ArrayList<>();

        for (Armazem armazem :
                lista) {
            nomes.add(armazem.getNome());
        }

        return nomes;
    }
    public Armazem getById(int id){
        Armazem armazem = null;
        ArrayList<Armazem> lista = getLista();

        for (Armazem arm : lista) {
            if(arm.getNumero() == id){
                armazem = arm;
                break;
            }
        }

        return armazem;
    }

    public Armazem getByName(String name){
        Armazem armazem = null;
        ArrayList<Armazem> lista = getLista();

        for (Armazem arm : lista) {
            if(arm.getNome() == name){
                armazem = arm;
                break;
            }
        }

        return armazem;
    }

    public Armazem getByIdAndByName(int id, String name){
        Armazem armazem = null;
        ArrayList<Armazem> lista = getLista();

        for (Armazem arm : lista) {
            if(arm.getNumero() == id && arm.getNome() == name){
                armazem = arm;
                break;
            }
        }

        return armazem;
    }

    public Armazem validateInputsAndSearchArmazem(String id, String name) throws InvalidValueException {
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

    public void cadastrar(Integer numero, String nome) throws ArmazemAlreadyExistsException, InvalidValueException {
        ArrayList<Armazem> armazens = getLista();
        boolean exists = false;

        if(nome.isBlank()) throw new InvalidValueException("Preencha os campos correctamente!");

        Armazem armazem= new Armazem(numero, nome, LocalDateTime.now());

        for (Armazem arm:
                armazens) {
            if (arm.getNome().equalsIgnoreCase(armazem.getNome())) {
                exists = true;
                break;
            }
        }

        if(!exists)
            armazens.add(armazem);
        else
            throw new ArmazemAlreadyExistsException(armazem);


        try {
            saveAtFile(armazens);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void limparLista() {
        ArrayList<Armazem> armazems = getLista();
        armazems.clear();
        try {
            saveAtFile(armazems);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public int getNextPosition() {
        ArrayList<Armazem> armazems = (ArrayList<Armazem>) getLista();
        return armazems.size() + 1;
    }

    public Armazem getByIndex(int index){
        return getLista().get(index);
    }

    public int getSize(){
        return getLista().size();
    }


    public void deleteById(int id) throws IOException {
        ArrayList<Armazem> armazems = getLista();
        armazems.remove(id);
        saveAtFile(armazems);
    }

    public  boolean actualizar(int id, String nome) throws InvalidValueException {
        ArrayList<Armazem> armazems = getLista();
        if(!nome.isBlank()){
            armazems.get(id).setNome(nome);

            try {
                saveAtFile(armazems);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }

        throw new InvalidValueException("Preencha os campos devidamente");

    }
}
