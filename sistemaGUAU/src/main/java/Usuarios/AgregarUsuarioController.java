/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

import JPA.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author GuillePC
 */
public class AgregarUsuarioController implements Initializable {

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtContra;
    @FXML
    private Label labelTitulo;
    
    private  UsuarioDAO usuario_dao = new UsuarioDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btnGuardarHandle(ActionEvent event) {
        byte[] contra = txtContra.getText().getBytes();
       
  

        if ((!txtNombre.getText().isEmpty()) && (!txtUsuario.getText().isEmpty()) && (!txtContra.getText().isEmpty())) {
              Usuario newUsuario = new Usuario();
            newUsuario.setNombre(txtNombre.getText());
            newUsuario.setNombreusuario(txtUsuario.getText());
            newUsuario.setContraseña(contra);
            try {
                usuario_dao.AgregarUsuario(newUsuario);
                btnCancelarHandle(event);
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, "Error al ingresar"+ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    @FXML
    private void btnLimpiarHandle(ActionEvent event) {
        txtNombre.setText("");
        txtUsuario.setText("");
        txtContra.setText("");
    }

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

    @FXML
    private void txtNombreTyped(KeyEvent event) {
        char[] string = event.getCharacter().toCharArray();

        for (char c : string) {
            if ((!Character.isLetter(c))) {
                event.consume();
                 JOptionPane.showMessageDialog(null, "Solo se permiten usar letras", "Error", JOptionPane.ERROR_MESSAGE);
                txtNombre.setText("");
            } else {
                txtUsuario.setText(txtNombre.getText() + c);
            }

        }
    }

    @FXML
    private void txtContraTyped(KeyEvent event) 
    {
//         if (txtContra.getText().length()>=8) 
//        {
//            event.consume();
//        }
         char[] string = event.getCharacter().toCharArray();

        for (char c : string) {
            if ((!Character.isLetter(c)) && (!Character.isDigit(c))) {
                event.consume();
                 JOptionPane.showMessageDialog(null, "Solo se permiten usar letras y numeros", "Error", JOptionPane.ERROR_MESSAGE);
                txtContra.setText("");
            } 

        }
       
    }

}
