package sistemaguau;

import Clientes.ClienteDAO;
import JPA.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author José Sánchez
 */
public class SistemaGUAU extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Login/FormLogin.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        ClienteDAO cliente_dao = new ClienteDAO();
        Cliente a = new Cliente();
                
        a.setNombre("José Sánchez");
        a.setNit("1269253-0");
        a.setTelefono("55687069");
        a.setDireccion("Ciudad");
        a.setTipocliente("Frecuente");
        a.setMayorista(true);
        
        try {
            cliente_dao.AgregarCliente(a);
        } catch (Exception ex) {
            Logger.getLogger(SistemaGUAU.class.getName()).log(Level.SEVERE, null, ex);
        }
        launch(args);
        
    }
}
