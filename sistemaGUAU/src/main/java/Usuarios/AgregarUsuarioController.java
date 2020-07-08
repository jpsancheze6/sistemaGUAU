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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btnGuardarHandle(ActionEvent event) {
//        byte[] contra = txtContra.getText().getBytes();
//        UsuarioDAO usuario_dao = new UsuarioDAO();
//        Usuario newUsuario = new Usuario();
//        newUsuario.setNombre(txtNombre.getText());
//        newUsuario.setNombreusuario(txtUsuario.getText());
//        newUsuario.setContraseña(contra);
//        
//        
//        try 
//        {
//         usuario_dao.AgregarUsuario(newUsuario);
//        } catch (Exception ex) {
//         JOptionPane.showMessageDialog(null, "Error");
//        }
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
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Usuarios/FormUsuarios.fxml"));
//        Parent root1 = (Parent) fxmlLoader.load();
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root1));
//        stage.setTitle("MEVECOM <>");
//        stage.setResizable(false);
//        stage.show();
//        //Cerrar ventana actual
//        Stage actual = (Stage) btnCancelar.getScene().getWindow();
//        actual.close();
    }
    
}
