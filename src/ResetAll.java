import controllers.*;

public class ResetAll {

    public static void main(String[] args) {
        new FornecedorController().limparLista();
        new ProdutoController().limparLista();
        new ArmazemController().limparLista();
        new AdministradorController().limparLista();
        new ClienteController().limparLista();
        new VendaController().limparLista();
        new VendaProdutoController().limparLisa();

    }
}
