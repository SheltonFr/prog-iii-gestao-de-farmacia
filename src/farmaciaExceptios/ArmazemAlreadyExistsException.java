package farmaciaExceptios;

import model.Administrador;
import model.Armazem;

public class ArmazemAlreadyExistsException extends Exception{

    public ArmazemAlreadyExistsException(Armazem armazem){
        super("Ja existe um armazem cadastrado como: " + armazem.getNome() + "!");
    }
}
