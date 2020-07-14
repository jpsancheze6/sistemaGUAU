/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

import JPA.Usuario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.Robot;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author GuillePC
 */
public class ModificarUsuarioController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnLimpiar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private Label labelTitulo;

    private Usuario usuario;

    private UsuarioDAO usuario_dao = new UsuarioDAO();
    @FXML
    private JFXPasswordField txtContras;
    @FXML
    private JFXPasswordField txtConfirmarContra;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtConfirmarContra.setLabelFloat(true);
        txtContras.setLabelFloat(true);
        txtNombre.setLabelFloat(true);
        txtUsuario.setLabelFloat(true);
        
        try ( FileReader fileReader = new FileReader("usuario.txt")) {
            int id = fileReader.read();
            usuario = usuario_dao.getUsuarioByID(id);
            txtNombre.setText(this.usuario.getNombre());
            txtUsuario.setText(this.usuario.getNombreusuario());
            txtContras.setText("");

        } catch (FileNotFoundException e) {
            // Exception handling
        } catch (IOException e) {
            // Exception handling
        }
    }
    
    //---------------------------------------------------------------Mensaje de Confimarcion
    public static String showConfirm(String title, String message, String... options) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.DECORATED);
        alert.setTitle("CONFIRMAR");
        alert.setHeaderText(title);
        alert.setContentText(message);

        //To make enter key press the actual focused button, not the first one. Just like pressing "space".
        alert.getDialogPane().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                event.consume();
                try {
                    Robot r = new Robot();
                    r.keyPress(java.awt.event.KeyEvent.VK_SPACE);
                    r.keyRelease(java.awt.event.KeyEvent.VK_SPACE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        List<ButtonType> buttons = new ArrayList<>();
        for (String option : options) {
            buttons.add(new ButtonType(option));
        }

        alert.getButtonTypes().setAll(buttons);

        Optional<ButtonType> result = alert.showAndWait();
        String Op = result.get().getText();
        if (result.get().getText().equals("Si")) {
            return "Si";
        } else {
            return "No";
        }
    }

    //---------------------------------------------------------------Mensaje de Error
    public static void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("ERROR");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }

    //-------------------------------------------------Guardar
    @FXML
    private void btnGuardarHandle(ActionEvent event) {

        byte[] contra = txtContras.getText().getBytes();
        if ((!txtNombre.getText().isEmpty()) && (!txtContras.getText().isEmpty()) && (!txtUsuario.getText().isEmpty())) {

            if (txtContras.getText().equals(txtConfirmarContra.getText())) {
                usuario.setNombre(txtNombre.getText());
                usuario.setNombreusuario(txtUsuario.getText());
                usuario.setPassword(contra);

                try {
                    usuario_dao.EditarUsuario(usuario);
                    btnCancelarHandle(event);
                } catch (Exception ex) {
                    showError("Error al actualizar los datos", "Debido a un error no se puedo actualizar el usuario");
                }

            } else {
                showError("Confirmar contraseña", "Las contraseñas no coinciden");
                txtConfirmarContra.setText("");
            }

        } else {
            showError("Campos Vacios", "Llenar todos los campos");
        }
    }

    //--------------------------------------Limpiar Campos
    @FXML
    private void btnLimpiarHandle(ActionEvent event) {
        txtNombre.setText("");
        txtUsuario.setText("");
        txtContras.setText("");

    }

    //--------------------------------------------Cancelar
    @FXML
    private void btnCancelarHandle(ActionEvent event) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Usuarios/FormUsuarios.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnCancelar.getScene().getWindow();
        actual.close();
    }

}
