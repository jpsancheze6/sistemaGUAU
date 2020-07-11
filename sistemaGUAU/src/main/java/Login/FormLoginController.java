/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import JPA.Usuario;
import Usuarios.UsuarioDAO;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author José Sánchez
 */
public class FormLoginController implements Initializable {

    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXPasswordField txtPassword;

    UsuarioDAO usuario_dao = new UsuarioDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtUsuario.setLabelFloat(true);
        txtPassword.setLabelFloat(true);
    }

    //------------ Error
    public static void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("ERROR");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }
    // Warning

    public static void showWarning(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Advertencia");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }

    @FXML
    public void aceptar(ActionEvent e) throws IOException {

        String nombreUsuario = txtUsuario.getText();
        String pass = txtPassword.getText();
        byte[] contringre = txtPassword.getText().getBytes();
        int fin = 0;
        boolean v =false;

        if (nombreUsuario.length() != 0) {
            List<Usuario> listaUsuarios = usuario_dao.getUsuarios();
            for (Usuario next : listaUsuarios) {
                fin++;
                if (next.getNombreusuario().equals(nombreUsuario)) {
                    v=true;
                    byte[] contrabuscada = next.getPassword();
                    int rescompa = Arrays.compare(contringre, contrabuscada);

                    if (rescompa == 0) {
                        // Llamar listaUsuarios una nueva ventana
                        Parent root = FXMLLoader.load(getClass().getResource("/Principal/FormPrincipal.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("MEVECOM <>");
                        stage.setResizable(false);
                        stage.show();
                        //Cerrar ventana actual
                        Stage actual = (Stage) txtPassword.getScene().getWindow();
                        actual.close();
                    } else {
                        showError("Contraseña incorrecta", "La contraseña ingresada no corresponde al Usuario");
                        txtPassword.setText("");
                    }
                } else if ((usuario_dao.getUsuarios().size() == fin) && v==false) {
                    showError("Usuario no encontrado", "El usuario ingresado no esta registrado en la Base de Datos");
                }
            }
//           
        } else {
            showWarning("Falta nombre", "Por favor ingrese el usuario para iniciar sesion");
        }

    }

}
