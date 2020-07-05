package Usuarios;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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

    public final UsuarioDAO usuario_dao = new UsuarioDAO();

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
        panelCrear.setVisible(true);
        txtNombre.setText("");
        txtUsuario.setText("");
        txtContrasena.setText("");
        txtConfirmar.setText("");
        cbAdmin.setSelected(false);
        cbRegular.setSelected(false);
    }

    @FXML
    private void guardarUsuario() {
        System.out.println("SI jejee");
        Usuario usuario = new Usuario(this.getID());
        usuario.setNombre("Juanito");
        usuario.setContraseña("asf");
        usuario.setDireccion("ciudad");
        usuario.setNit("12692533333");
        usuario.setNombreusuario("juanes");
        usuario.setPuesto("lagarton");
        usuario.setTelefono("1");
        usuario_dao.agregarUsuario(usuario);
    }

    private int getID() {
        List<Usuario> allPersonas = usuario_dao.obtenerUsuarios();
        int maxID = 0;

        if (!allPersonas.isEmpty()) {
            for (Usuario p : allPersonas) {
                if (p.getIdUsuario()> maxID) {
                    maxID = p.getIdUsuario();
                }
            }
        }
        return ++maxID;
    }

    @FXML
    private void editarUsuario(ActionEvent event) {
        btnRegistrar.setVisible(false);
        btnEditar.setVisible(true);
        panelCrear.setVisible(true);
        txtNombre.setText("");
        txtUsuario.setText("");
        txtContrasena.setText("");
        txtConfirmar.setText("");
        cbAdmin.setSelected(false);
        cbRegular.setSelected(false);
    }

    @FXML
    public void regresar(ActionEvent event) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Principal/FormPrincipal.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnEditar.getScene().getWindow();
        actual.close();
    }

}
