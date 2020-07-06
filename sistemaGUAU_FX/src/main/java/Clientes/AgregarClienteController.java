package Clientes;

import JPA.Cliente;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author José Sánchez
 */
public class AgregarClienteController implements Initializable {

    @FXML
    Button btnGuardar;

    @FXML
    public void regresar(ActionEvent event) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormClientes.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnGuardar.getScene().getWindow();
        actual.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnGuardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ClienteDAO cliente_dao = new ClienteDAO();
                Cliente a = new Cliente(3, "Jejeje", "CF", "NaN", "Ciudad", "Bueno", true);

                try {
                    cliente_dao.AgregarCliente(a);
                } catch (Exception ex) {
                    Logger.getLogger(AgregarClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
