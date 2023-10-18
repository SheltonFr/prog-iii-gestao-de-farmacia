import controllers.AdministradorController;
import view.administrador.Login;
import view.administrador.CadastroAdmin;
import view.splash.SplashScreen;

public class App {
    public static void main(String[] args) {

        SplashScreen splashScreen = new SplashScreen(4500);
        splashScreen.showSpalshAndExit();
        AdministradorController controller = new AdministradorController();

        if(controller.getLista().isEmpty())
            new CadastroAdmin();
        else
            new Login();
    }
}
