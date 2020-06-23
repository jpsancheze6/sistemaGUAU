/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author GuillePC
 */
public class AgregarProveedorController implements Initializable {

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnRegresar;
    @FXML
    private TextField txtNombre;
    @FXML
    private ComboBox<?> cmbProveedor;
    @FXML
    private AnchorPane AgregarProveedor;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtEmpresa;

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
    private void btnLimpiarHandle(ActionEvent event) {
    }

    @FXML
    private void btnCancelarHandle(ActionEvent event) {
    }

    @FXML
    private void btnRegresarHandle(ActionEvent event) {
    }

    
}
