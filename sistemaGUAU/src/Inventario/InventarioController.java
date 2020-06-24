/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventario;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author GuillePC
 */
public class InventarioController implements Initializable {

    @FXML
    private AnchorPane APaneInventario;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnAgregarProducto;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnRegresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAgregarProductoHandel(ActionEvent event) {
    }

    @FXML
    private void btnModificarHandle(ActionEvent event) {
    }

    @FXML
    private void btnRegresarHandle(ActionEvent event) {
    }
    
}
