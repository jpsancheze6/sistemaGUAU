/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author GuillePC
 */
public class ModificarUsuarioController implements Initializable {

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
        // TODO
    }    

    @FXML
    private void btnGuardarHandle(ActionEvent event) {
    }

    @FXML
    private void btnLimpiarHandle(ActionEvent event) 
    {
        txtNombre.setText("");
        txtUsuario.setText("");
        txtContra.setText("");
        
    }

    @FXML
    private void btnCancelarHandle(ActionEvent event) throws IOException 
    {
        //Llamar a una nueva ventana
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Usuarios/FromUsuario.fxml"));
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
