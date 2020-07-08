package sistemaguau;

import Clientes.ClienteDAO;
import JPA.Cliente;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author José Sánchez
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Login/FormLogin.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        ClienteDAO cliente_dao = new ClienteDAO();
        String nit = "1269253-0";
        String nombre = "Prueba";
        String telefono = "123";
        String direccion = "Gotham";
        String tipo_cliente = "Frecuente";
        boolean mayorista = true;

        Cliente a = new Cliente();
        a.setDireccion(direccion);
        a.setMayorista(mayorista);
        a.setNit(nit);
        a.setNombre(nombre);
        a.setTelefono(telefono);
        a.setTipocliente(tipo_cliente);
        try {
            cliente_dao.AgregarCliente(a);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        launch(args);
    }

}
