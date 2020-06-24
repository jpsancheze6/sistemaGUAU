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
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author GuillePC
 */
public class ProveedoresController implements Initializable {

    @FXML
    private AnchorPane APaneProveedores;
    @FXML
    private TableView<?> tblProveedores;
    @FXML
    private Button btnCrearProv;
    @FXML
    private Button btnModifProve;
    @FXML
    private Button btnDeshProvee;
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
    private void btnCrearHandle(MouseEvent event) {
    }

    @FXML
    private void btnCrearProvHandle(ActionEvent event) {
    }

    @FXML
    private void btnModificarProveedorHandle(MouseEvent event) {
    }

    @FXML
    private void btnModifProveHandle(ActionEvent event) {
    }

    @FXML
    private void btnDeshabilitarProveedorHandle(MouseEvent event) {
    }

    @FXML
    private void btnDeshProveeHandle(ActionEvent event) {
    }

    @FXML
    private void btnRegresarHandle(MouseEvent event) {
    }

    @FXML
    private void btnRegresarHandle(ActionEvent event) {
    }
    
}
