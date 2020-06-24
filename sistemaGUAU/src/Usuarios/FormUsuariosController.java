package Usuarios;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author José Sánchez
 */
public class FormUsuariosController implements Initializable {

    @FXML
    private Button btnCrearUsuario, btnEditarUsuario, btnRegistrar, btnEditar;
    @FXML
    private Pane panelCrear;
    @FXML
    private TextField txtNombre, txtUsuario, txtContrasena, txtConfirmar;
    @FXML
    private CheckBox cbAdmin, cbRegular;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void registrarUsuario(ActionEvent event) {
        btnEditar.setVisible(false);
    }
    
    @FXML
    private void editarUsuario(ActionEvent event) {
        btnRegistrar.setVisible(false);
    }
    
}
