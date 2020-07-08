package Ventas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
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
public class RegistrarVentaController implements Initializable {

    @FXML
    private TextField txtProducto, txtCantidad, txtDescuento;
    @FXML
    private Button btnBuscar, btnAgregar, btnEliminar, btnCancelar, btnGuardar, btnRegresar;
    @FXML
    private DatePicker Fecha;
    @FXML
    private ComboBox<?> cmbCliente;
    @FXML
    private Label labelTotal;
    
    //Tabla para mostrar los productos
    @FXML
    private TableView<?> tblProductos;
    @FXML
    private TableColumn<?, ?> colProducto;
    @FXML
    private TableColumn<?, ?> colPrecio;
    @FXML
    private TableColumn<?, ?> colExistencias;
    
    //Tabla final de la venta
    @FXML
    private TableView<?> tblVenta;
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
    
}
