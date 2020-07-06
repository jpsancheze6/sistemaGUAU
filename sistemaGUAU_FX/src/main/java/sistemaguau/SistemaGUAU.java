package sistemaguau;

import Clientes.ClienteDAO;
import JPA.Cliente;
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
        ClienteDAO cliente_dao = new ClienteDAO();
        Cliente a = new Cliente();
        a.setIdCliente(1);
        a.setDireccion("Ciudad");
        a.setMayorista(true);
        a.setNit("CF");
        a.setNombre("jayos");
        a.setTelefono("NaN");
        a.setTipocliente("Bueno");

        cliente_dao.AgregarCliente(a);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
