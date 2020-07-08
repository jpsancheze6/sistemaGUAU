package Compras;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author izasj
 */
public class RegistrarCompraController implements Initializable {

    @FXML
    private TextField txtProducto, txtCantidad;
    @FXML
    private Button btnBuscar, btnAgregar, btnCancelar, btnGuardar, btnRegresar, btnEliminar;
    @FXML
    private DatePicker Fecha;
    @FXML
    private Label labelTotal;
    @FXML
    private ComboBox<?> cmbProveedor;
    
    //Tabla para mostrar productos
    @FXML
    private TableView<?> tblProductos;
    @FXML
    private TableColumn<?, ?> colProducto;
    @FXML
    private TableColumn<?, ?> colPrecio;
    @FXML
    private TableColumn<?, ?> colExistencias;
    
    //Tabla para mostrar compra final
    @FXML
    private TableView<?> tblCompra;
    @FXML
    private TableColumn<?, ?> colProducto2;
    @FXML
    private TableColumn<?, ?> colPrecio2;
    @FXML
    private TableColumn<?, ?> colCantidad2;
    @FXML
    private TableColumn<?, ?> colTotal2;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void regresar(ActionEvent event) {
    }
    
}
