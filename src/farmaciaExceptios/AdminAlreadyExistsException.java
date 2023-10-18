package farmaciaExceptios;

import model.Administrador;

public class AdminAlreadyExistsException extends Exception{

    public AdminAlreadyExistsException(Administrador adm){
        super("Nome de utilizador " + adm.getUsername() + " indisponivel!");
    }
}
