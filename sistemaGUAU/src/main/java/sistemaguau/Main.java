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
        stage.setTitle("Mevecon<Login>");
        stage.show();
    }

    public static void main(String[] args) {
        //ClienteDAO cliente_dao = new ClienteDAO();
        //try {
        //    cliente_dao.getClientes();
        //} catch (Exception ex) {
        //    System.out.println(ex);
        //}

        launch(args);
    }

}
