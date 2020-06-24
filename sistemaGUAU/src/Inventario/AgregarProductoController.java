/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class AgregarProductoController implements Initializable {

    @FXML
    private AnchorPane APaneAgregarProducto;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtNit;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtCategoria;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtMarca1;
    @FXML
    private ComboBox<?> cmbProveedor;

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

    private void btnRegresarHandle(ActionEvent event) 
    {
       System.out.println("Presionado");
       
       FXMLLoader loader = new FXMLLoader (getClass().getResource("/Inventario/AgregarProducto.fxml"));
        
    }

    @FXML
    private void cmbProveedor(ActionEvent event) {
    }
    
}
